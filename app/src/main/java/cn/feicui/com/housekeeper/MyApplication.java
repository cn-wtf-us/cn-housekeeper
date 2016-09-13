package cn.feicui.com.housekeeper;

import android.app.Application;
import android.util.Log;

import cn.feicui.com.housekeeper.database.MySQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class MyApplication extends Application {

    public MySQLiteOpenHelper h;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "onCreate: ");
        //创建数据库操作
        h = new MySQLiteOpenHelper(
                this,
                MySQLiteOpenHelper.DATABASE_NAME,
                null,
                MySQLiteOpenHelper.DATABASE_VERSION
        );

    }
}
