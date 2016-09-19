package cn.feicui.com.houserkeeper.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class MyAssetManager {

    private static final String TAG = "MyAssetManager";
    /**
     * 返回的file是数据库文件存放的位置
     * @param context
     * @return path 如果不为null,拷贝成功，否则失败
     */
    public String copyDbFileToSd(Context context)
    {
        AssetManager assetManager = context.getAssets();
        String path = "db/commonnum.db";
        try {
            InputStream inputStream = assetManager.open(path);

            //包数据库文件保存到内置存储 file目录下
            File filesDir = context.getFilesDir();
            File file  = new File(filesDir,"commonnum.db");

            FileOutputStream fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);

            BufferedOutputStream bufo = new BufferedOutputStream(fileOutputStream);
            BufferedInputStream bufi = new BufferedInputStream(inputStream);

            byte[] buf = new byte[1024];
            int eof = 0;
            while ((eof = bufi.read(buf)) != -1) {
                bufo.write(buf,0,eof);
                bufo.flush();
            }
            //关流，在finally中
            bufi.close();
            bufo.close();
            Log.d(TAG, "save data success");
            return file.getName();
        } catch (IOException e) {
            Log.d(TAG, path+"路径错误");
            e.printStackTrace();
        }
        return null;

    }

}
