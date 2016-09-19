package cn.feicui.com.houserkeeper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.feicui.com.houserkeeper.adapter.TelClassAdapter;
import cn.feicui.com.houserkeeper.entity.TelClassInfo;
import cn.feicui.com.houserkeeper.util.DBRead;
import cn.feicui.com.houserkeeper.util.MyAssetManager;

public class TelClassActivity extends BaseActivity {

    private static final String TAG = "TelClassActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!DBRead.isExistsTeldbFile()) {
            //1.
            MyAssetManager assetManager = new MyAssetManager();
            //path 就是db文件存放的路径
            String path = assetManager.copyDbFileToSd(this);
            Log.d(TAG, "onCreate: "+path);
            Toast.makeText(TelClassActivity.this, "ddd", Toast.LENGTH_SHORT).show();
        }
        final ArrayList<TelClassInfo> telClassInfos = DBRead.readTeldbClasslist();

        ListView listView = (ListView) findViewById(R.id.show_tel_class_list_view);

        TelClassAdapter adapter = new TelClassAdapter(this,telClassInfos);

        listView.setAdapter(adapter);

        //给listview设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                AdapterView<?> parent,//parent引用的指向是listview对象
                View view,
                int position,//就是条目所在的位置 从0开始
                long id//是条目的id值，是adapter 的getItemId（position）方法设置的
            ) {
                //当条目被点击，松开的时候，触发该方法
                Toast.makeText(TelClassActivity.this, id+"=="+position, Toast.LENGTH_SHORT).show();
                //a.跳转到TelClassActivity界面，并传递idx值
                Bundle bundle = new Bundle();
                bundle.putInt("idx",telClassInfos.get(position).getIdx());
                startActivityWithBundle(TelNumActivity.class,bundle);
            }
        });
    }
}
