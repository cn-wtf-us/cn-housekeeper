package cn.feicui.com.houserkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import cn.feicui.com.houserkeeper.adapter.TelNumAdapter;
import cn.feicui.com.houserkeeper.entity.TelNumberInfo;
import cn.feicui.com.houserkeeper.util.DBRead;

public class TelNumActivity extends BaseActivity {

    private ListView listView;

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

    }

    private void asyncLoadData(final int idx) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                ArrayList<TelNumberInfo> telNumberInfos = DBRead.readTeldbTable(idx);
                //d把数据展示到listview中
                listView.setAdapter(new TelNumAdapter(TelNumActivity.this,telNumberInfos));
            }
        }.start();

    }
}
