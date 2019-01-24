package plugin.com.pluginpro;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import plugin.com.aplugin.APluginInterface;

public class ProxyActivity extends Activity {
    //要跳转的插件的activity 的全类名
    private String className;
    private APluginInterface aPluginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG","onCreate");
        className=getIntent().getStringExtra("className");
        try {
            Class activityClass=getClassLoader().loadClass(className);
            Constructor constructor=activityClass.getConstructor(new Class[]{});
            Object instance=constructor.newInstance(new Object[]{});
            aPluginInterface= (APluginInterface) instance;
            aPluginInterface.atach(this);

            Bundle bundle=new Bundle();
            aPluginInterface.onCreate(bundle);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG","onStart");
        if(aPluginInterface!=null){
            aPluginInterface.onStart();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String classForName=intent.getStringExtra("className");
        Intent intent1=new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",classForName);
        super.startActivity(intent1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(aPluginInterface!=null){
            return aPluginInterface.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(aPluginInterface!=null){
            aPluginInterface.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(aPluginInterface!=null){
            aPluginInterface.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(aPluginInterface!=null){
            aPluginInterface.onDestroy();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(aPluginInterface!=null){
            aPluginInterface.onResume();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}
