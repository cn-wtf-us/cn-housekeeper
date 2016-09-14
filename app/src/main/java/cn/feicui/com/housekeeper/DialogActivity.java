package cn.feicui.com.housekeeper;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.btn_alert).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_popu).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_custom_alert).setOnClickListener(this);
        findViewById(R.id.btn_progress).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert:
                //创建最基本的构建提醒对话框 alert dialog
                createAlert();
                break;
            case R.id.btn_date:
                break;
            case R.id.btn_popu:
                break;
            case R.id.btn_time:
                break;
            case R.id.btn_custom_alert:
                break;
            case R.id.btn_progress:
                break;

        }
    }

    private void createAlert() {
        //AlertDialog的创建对象，需要Builder类完成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置是否可以通过回退按键取消对话框,默认是可以取消
        builder.setCancelable(false);
        builder.setIcon(R.drawable.i1)
                .setTitle("alertdialog")
                .setMessage("你确定要退出当前界面？")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel",null)
                .create()
                .show();
    }
}
