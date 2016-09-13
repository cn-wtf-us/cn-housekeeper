package cn.feicui.com.housekeeper.util;

import android.os.Environment;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class SdCardUtil {

    /*
    判断sdcard是否挂载,并可写数据
    可写一定可以可读，可读不一定可写
     */
    public static boolean isExternalStorageWritable() {
        /*
        Environment 这个类来获取sdcard状态
        Environment.MEDIA_MOUNTED 挂载
         */
        //获取当前sdcard实际的状态信息
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
        /*
        *   if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        */
    }

    /*
        判断sdcard是否可读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
        /*
        官方写法：
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
        */

    }
}
