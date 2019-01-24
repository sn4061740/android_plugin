package plugin.com.pluginpro;

import android.com.permission.IPermissionListener;
import android.com.permission.RunActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);

        findViewById(R.id.btnLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(v);
            }
        });
        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1(v);
            }
        });

        RunActivity.toActivity(this, new IPermissionListener() {
            @Override
            public void onSuccess() {
                Log.e("TAG","授权成功...");
            }
            @Override
            public void onError() {
                Log.e("TAG","授权失败...");
            }
        });
    }

    public void load(View view){
        File file=new File(Environment.getExternalStorageDirectory()+"/"+"plugin.apk");
        if(!file.exists()){
            Log.e("TAG","文件不存在");
            return;
        }
        PluginManager.getInstance().load(this,file.getAbsolutePath());
    }
    public void click1(View view){
        Intent intent=new Intent(this,ProxyActivity.class);
        intent.putExtra("className",PluginManager.getInstance().getEntryActivityName());
        startActivity(intent);
    }

}
