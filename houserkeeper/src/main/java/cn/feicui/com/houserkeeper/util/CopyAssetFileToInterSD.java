package cn.feicui.com.houserkeeper.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class CopyAssetFileToInterSD {

    private static final String TAG = "CopyAssetFileToInterSD";

    /**
     * 拷贝assets目录下的文件到内置存储中
     * @param context 上下文
     * @param fromFile asset目录下的文件
     * @param toFile    文件名，该文件会被存放在内置存储目录files下 即：data/data/包名/files/toFile
     * @return 内置存储存放文件的路径
     */
    public static String  copyAssetFileToInterSD(Context context,String fromFile,String toFile) throws IOException {
        //得到assets目录的管理者AssetManager的对象
        AssetManager manager = context.getAssets();
        //异常谁调用，就谁处理
        InputStream inputStream = manager.open(fromFile);

        //如果toFile文件不存在，调用这个方法，会自动创建
        OutputStream outputStream = context.openFileOutput(toFile, Context.MODE_PRIVATE);
        //读写数据的操作
        boolean isSuccess = copyData(
                inputStream,//数据的源头
                outputStream//数据存放目的地
        );

        if (isSuccess) {
            //拷贝成功
            Log.d(TAG, "copyAssetFileToInterSD: successed");
            File file = new File(context.getFilesDir(),toFile);
            return file.getPath();
        }
        return null;
    }

    private static boolean copyData(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedInputStream bufi = null;
        BufferedOutputStream bufo = null;
        try {
            //数据源
             bufi = new BufferedInputStream(inputStream);

             bufo = new BufferedOutputStream(outputStream);

            byte[] buf = new byte[8*1024];
            int eof;
            while((eof = bufi.read(buf))!=-1)
            {
                bufo.write(buf,0,eof);
                bufo.flush();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //可能是流已经关闭或者其他导致的异常。。。
        }
        finally {
            if (bufi!=null) {
                bufi.close();
            }
            if (bufo!=null) {
                bufo.close();
            }
        }
        return false;
    }

}
