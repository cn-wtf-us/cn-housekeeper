package cn.feicui.com.housekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.feicui.com.housekeeper.entity.DataKnowledge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                DataKnowledge.strs
        );
        assert listView != null; //等价于   if (listView != null)
//        if (listView != null)
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当条目item被点击的时候回调此方法
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, AnimationActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, WriterReadActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, SqliteDatabaseActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, DialogActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });


    }
}
