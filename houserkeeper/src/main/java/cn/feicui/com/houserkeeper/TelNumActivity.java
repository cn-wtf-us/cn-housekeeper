package cn.feicui.com.houserkeeper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.feicui.com.houserkeeper.adapter.TelNumAdapter;
import cn.feicui.com.houserkeeper.entity.TelNumberInfo;
import cn.feicui.com.houserkeeper.util.DBRead;

public class TelNumActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<TelNumberInfo> telNumberInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_num);

        listView = (ListView) findViewById(R.id.list);
        //b.获取上个界面传递过来的intent对象，并取出里面的值。。。
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            int idx = (int) bundle.get("idx");
            //c通过DB类读取数据,是一个耗时的操作，应该开辟子线程来做
            asyncLoadData(idx);
        }
        //给listview的每个条目(Item)设置点击事件，不是View.OnClickListener
        listView.setOnItemClickListener(this);
    }

    private void asyncLoadData(final int idx) {
        final MyApplication application = (MyApplication) getApplication();
        new Thread() {
            @Override
            public void run() {
                super.run();
                telNumberInfos = DBRead.readTeldbTable(application.telFile, idx);
                //d把数据展示到listview中
                listView.setAdapter(new TelNumAdapter(TelNumActivity.this, telNumberInfos));
            }
        }.start();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //当条目被点击的时候回调此方法
//        Toast.makeText(TelNumActivity.this, position + "", Toast.LENGTH_SHORT).show();
        //拨打电话
        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        String num = telNumberInfos.get(position).getNumber();//第一种思路
        TelNumberInfo telNumberInfo = (TelNumberInfo) parent.getItemAtPosition(position);//获取对应position位置的数据
        Uri data = Uri.parse("tel:" + telNumberInfo.getNumber());
        callIntent.setData(data);
        /*
        java.lang.SecurityException: Permission Denial: starting Intent
         { act=android.intent.action.CALL dat=tel:xxxxxx cmp=com.android.phone/
         .OutgoingCallBroadcaster } from ProcessRecord{b322f0d0
         11823:cn.feicui.com.houserkeeper/u0a58} (pid=11823, uid=10058)
         requires android.permission.CALL_PHONE
         */
        //校验用户是否给了权限没给没反应
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //用户过给权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.CALL_PHONE
                }, 1);
            } else {
                Toast.makeText(TelNumActivity.this, "请给我打电话权限-----------", Toast.LENGTH_SHORT).show();
            }
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            Toast.makeText(TelNumActivity.this, "我有打电话权限", Toast.LENGTH_SHORT).show();
        }
        startActivity(callIntent);
    }
}
