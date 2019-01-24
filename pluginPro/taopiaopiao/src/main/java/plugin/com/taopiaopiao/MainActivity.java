package plugin.com.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import plugin.com.base.taopiaopiao.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(that,"点击了toast",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(that,SecendActivity.class);
                startActivity(intent);
            }
        });
    }
}
