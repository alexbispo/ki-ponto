package com.artes.alexbispo.kiponto.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alex on 06/03/17.
 */

public abstract class AbstractModel extends SQLiteOpenHelper {

    public AbstractModel(Context context, int dbVersion) {
        super(context, "ki_ponto", null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + getTableName() + " (" + toSql() + ", created_at INTEGER NOT NULL" +
                ", updated_at INTEGER NOT NULL);";
        Log.d("AbstractModel#onCreate", sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + getTableName() + ";";
        db.execSQL(sql);
    }

    protected abstract String toSql();

    protected abstract String getTableName();

}
