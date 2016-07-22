package com.hadesky.cacw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**dbHelper
 * Created by 45517 on 2015/10/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "cacw.sqlite";
    public static final int VERSION = 2;


    public DatabaseHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表格task
        db.execSQL("CREATE TABLE Message (" +
                "from TEXT not null," +
                "to TEXT null," +
                "type Integer not null," +
                "content TEXT not null," +
                "hasRead Integer not null" + //1 为已读
                ")");

        db.execSQL("CREATE TABLE Users (" +
                "ObjectId text primary key," +
                "NickName Text" +
                "avatarUrl Text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
