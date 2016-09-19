package cn.feicui.com.houserkeeper.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

import cn.feicui.com.houserkeeper.entity.TelClassInfo;
import cn.feicui.com.houserkeeper.entity.TelNumberInfo;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class DBRead {
    /**
     * 通讯大全 File
     */
    public static File telFile;

    static {
        // 默认位置
        //等价于context.getfiledir()
        String dbFileDir = "data/data/cn.feicui.com.houserkeeper/files/";
        // 存储卡
        // String sdcardState = Environment.getExternalStorageState();
        // if (sdcardState.equals(Environment.MEDIA_MOUNTED)) {
        // dbFileDir = Environment.getExternalStorageDirectory() +
        // "/azyphone/cache";
        // }
        File fileDir = new File(dbFileDir);
        fileDir.mkdirs(); // 文件目录的创建
        telFile = new File(dbFileDir, "commonnum.db");
        LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
    }

    /**
     * 检测是否存在通讯大全 DB文件
     */
    public static boolean isExistsTeldbFile() {
        // 没有通讯大全 File
//        File toFile = telFile;
//        if (!toFile.exists() || toFile.length() <= 0) {
//            return false;
//        }
//        return true;
        return !telFile.exists() || telFile.length() <= 0;
    }

    /**
     * 读取telFile这个数据库文件中的 classlist这个表内的数据
     *
     * @throws Exception
     */
    public static ArrayList<TelClassInfo> readTeldbClasslist() {
        ArrayList<TelClassInfo> classListInfos = new ArrayList<TelClassInfo>();
        // 打开 DB文件
        SQLiteDatabase db = null;
        // 执行查询的 SQL语句 select * from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);
            LogUtil.d("DBRead", "read teldb classlist size: "
                    + cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor
                        .getString(cursor
                                .getColumnIndex("name"));
                //idx为 classlist表中电话的 ID，根据 idx值进行指定页面的跳转

                int idx = cursor.getInt(cursor
                        .getColumnIndex("idx"));
                TelClassInfo classListInfo = new TelClassInfo(
                        name, idx);
                classListInfos.add(classListInfo);
            }
        } catch (Exception e) {
            LogUtil.d("DBRead", "read teldb classlist failed");
        } finally {
            try {
                //关闭游标
                cursor.close();
                //关闭数据库，释放资源
                db.close();
            } catch (Exception e2) {
                throw e2;
            }
        }
        return classListInfos;
    }

    /**
     * 读取telFile这个数据库文件中的 tableN这个表内的数据(商家名称和电话号
     *
     * @throws Exception
     */
    public static ArrayList<TelNumberInfo> readTeldbTable(int idx) {
        ArrayList<TelNumberInfo> numberInfos = new ArrayList<>();
        //idx为classlist表中电话的 ID，根据 idx值进行指定页面的跳转
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            // 打开 DB文件
            db = SQLiteDatabase
                    .openOrCreateDatabase(telFile, null);
            // 执行查询的 SQL语句 select * from table +idx
            cursor = db.rawQuery(sql, null);
            LogUtil.d("DBRead", "read teldb number table size: "
                    + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor
                            .getString(cursor
                                    .getColumnIndex("name"));
                    String number = cursor
                            .getString(cursor
                                    .getColumnIndex("number"));
                    TelNumberInfo numberInfo = new TelNumberInfo(
                            name, number);
                    numberInfos.add(numberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            LogUtil.d("DBRead", "read teldb classlist failed");
        } finally {
            try {
                //关闭游标
                cursor.close();
                //关闭数据库，释放资源
                db.close();
            } catch (Exception e2) {
                throw e2;
            }
        }
        return numberInfos;
    }

}