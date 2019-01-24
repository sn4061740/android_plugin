package plugin.com.base.taopiaopiao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import plugin.com.aplugin.APluginInterface;

public class BaseActivity extends Activity implements APluginInterface {
    Activity that;

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("TAG","Taopiaopiao onCreate");
    }

    @Override
    public void setContentView(int layoutResID) {
        if(that==null){
            super.setContentView(layoutResID);
        }else{
            that.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(that==null){
            return super.findViewById(id);
        }else{
            return that.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if(that==null){
            return super.getClassLoader();
        }
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if(that==null){
            return super.getLayoutInflater();
        }
        return that.getLayoutInflater();
    }

    @Override
    public Resources getResources() {
        if(that==null){
            return super.getResources();
        }
        return that.getResources();
    }

    @Override
    public Window getWindow() {
        if(that==null){
            return super.getWindow();
        }
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        if(that==null){
            return super.getWindowManager();
        }
        return that.getWindowManager();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
        Log.e("TAG","Taopiaopiao onStart");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
        Log.e("TAG","Taopiaopiao onResume");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
        Log.e("TAG","Taopiaopiao onPause");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
        Log.e("TAG","Taopiaopiao onStop");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
        Log.e("TAG","Taopiaopiao onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void atach(Activity activity) {
        Log.e("TAG","Taopiaopiao atach 初始化");
        that=activity;
    }
}
