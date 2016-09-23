package cn.feicui.com.houserkeeper;

import android.app.Application;
import android.util.Log;

import java.io.IOException;

import cn.feicui.com.houserkeeper.util.CopyAssetFileToInterSD;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public String telFile;

    @Override
    public void onCreate() {
        super.onCreate();//做分享，推送，都来这里初始化一些数据
        //拷贝数据库文件到内置存储,只要执行一次，所有在MyApplication中执行
        try {
            telFile = CopyAssetFileToInterSD.copyAssetFileToInterSD(this, "db/commonnum.db", "commonnum.db");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "onCreate: failed copy");
        }
    }
}
