package cn.feicui.com.housekeeper.database;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public final class PersonContract {
//    SQLiteOpenHelper 是一个抽象类

    /* Inner class that defines the table contents

    * */
    public static abstract class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_name = "name";
        public static final String COLUMN_NAME_class = "class";
    }
}

