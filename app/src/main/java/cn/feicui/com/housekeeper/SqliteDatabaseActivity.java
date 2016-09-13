package cn.feicui.com.housekeeper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cn.feicui.com.housekeeper.database.PersonContract;
import cn.feicui.com.housekeeper.entity.Person;

public class SqliteDatabaseActivity extends AppCompatActivity {

    private static final String TAG = "SqliteDatabaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_database);
        final EditText et_id = (EditText) findViewById(R.id.et_id);
        final EditText et_name = (EditText) findViewById(R.id.et_name);
        final EditText et_class = (EditText) findViewById(R.id.et_class);

        //首先创建数据库，只需要做一次，放在那里做呢？
        MyApplication application = (MyApplication) getApplication();
        final SQLiteDatabase database = application.h.getWritableDatabase();

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存数据到数据库
                ContentValues values = new ContentValues();
                values.put(PersonContract.PersonEntry.COLUMN_NAME_ENTRY_ID,et_id.getText().toString());
                values.put(PersonContract.PersonEntry.COLUMN_NAME_name,et_name.getText().toString());
                values.put(PersonContract.PersonEntry.COLUMN_NAME_class,et_class.getText().toString());
                long insert = database.insert(PersonContract.PersonEntry.TABLE_NAME, null, values);
                Log.d(TAG, "insert"+insert);
            }
        });
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取数据库中的数据

                String selection = PersonContract.PersonEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";

                String[] selectionArg = new String[]{et_id.getText().toString()};
                Cursor cursor = database.query(
                        PersonContract.PersonEntry.TABLE_NAME,
                        new String[]{PersonContract.PersonEntry.COLUMN_NAME_name,
                                PersonContract.PersonEntry.COLUMN_NAME_class,
                                PersonContract.PersonEntry.COLUMN_NAME_ENTRY_ID
                        },
                        selection,
                        selectionArg,
                        null,
                        null,
                        null
                );
                int count = cursor.getCount();
                Log.d(TAG, "count: "+count);
                while (cursor.moveToNext()) {
                    Person p = new Person();
                    String name = cursor.getString(cursor.getColumnIndex(PersonContract.PersonEntry.COLUMN_NAME_name));
                    et_name.setText(name);
                    p.name = name;
                }
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = database.delete(
                        PersonContract.PersonEntry.TABLE_NAME,
                        null,//不给，删除所有数据
                        null
                );
                Log.d(TAG, "delete "+i);
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = PersonContract.PersonEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
                String[] selectionArg = new String[]{et_id.getText().toString()};
                ContentValues values = new ContentValues();
                values.put(PersonContract.PersonEntry.COLUMN_NAME_name,"110");
                database.update(
                        PersonContract.PersonEntry.TABLE_NAME,
                       values,
                        selection,
                        selectionArg
                );

            }
        });



    }
}
