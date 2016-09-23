package cn.feicui.com.housekeeper;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.feicui.com.housekeeper.biz.HelloIntentService;
import cn.feicui.com.housekeeper.biz.HelloService;

public class ServiceDemoActivity extends AppCompatActivity {

    private static final String TAG = "ServiceDemoActivity";
    private Intent intent;
    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动继承自IntentService服务，四大组件之一，通过Intent来过滤
                Intent i = new Intent(ServiceDemoActivity.this, HelloIntentService.class);
                i.putExtra("key", "value");
                startService(i);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动继承自Service服务
                intent = new Intent(ServiceDemoActivity.this, HelloService.class);
                startService(intent);
            }
        });
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定继承自Service服务
                intent = new Intent(ServiceDemoActivity.this, HelloService.class);
                bindService(
                        intent,
                        conn,
                        BIND_AUTO_CREATE
                );
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解绑服务
                if (conn != null) {
                    unbindService(conn);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    private void doUnbindService() {
    }
}
