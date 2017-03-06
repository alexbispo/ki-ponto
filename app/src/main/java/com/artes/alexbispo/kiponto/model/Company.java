package com.artes.alexbispo.kiponto.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.artes.alexbispo.kiponto.utils.DateUtils.DD_MM_YYYY;
import static com.artes.alexbispo.kiponto.utils.DateUtils.format;

/**
 * Created by alex on 06/03/17.
 */

public class Company extends AbstractModel {

    private static final int DATA_BASE_VERSION = 1;

    private Context context;

    private long id;
    private String name;
    private long startAt;
    private long leavingAt;
    private long createdAt;
    private long updatedAt;


    public Company(Context context) {
        super(context, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    protected String toSql() {
        return "id INTEGER PRIMARY KEY, name TEXT NOT NULL, start_at INTEGER" +
                ", leaving_at INTEGER";

    }

    @Override
    protected String getTableName() {
        return "companies";
    }

    public long getId() {
        return id;
    }

    public Company setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public long getStartAt() {
        return startAt;
    }

    public Company setStartAt(long startAt) {
        this.startAt = startAt;
        return this;
    }

    public long getLeavingAt() {
        return leavingAt;
    }

    public Company setLeavingAt(long leavingAt) {
        this.leavingAt = leavingAt;
        return this;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAttributes(Map<String, String> attributes){
        if(attributes == null || attributes.isEmpty()){
            throw new IllegalArgumentException("Map<String, String> attributes can't be null or empty!");
        }
        if (attributes.containsKey("name")) setName(attributes.get("name"));
        if (attributes.containsKey("startAt")) setStartAt(Long.parseLong(attributes.get("startAt")));
        if (attributes.containsKey("leavingAt")) setLeavingAt(Long.parseLong(attributes.get("leavingAt")));
    }

    @Override
    public String toString() {
        return getName() + " - " + format(startAt, DD_MM_YYYY) + " - " + format(leavingAt, DD_MM_YYYY);
    }

    public List<Company> all(){
        String sql = "SELECT * FROM " + getTableName() + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<Company> all = new ArrayList<>();

        while (cursor.moveToNext()){
            Company company = new Company(this.context);
            company.setId(cursor.getLong(cursor.getColumnIndex("id")));
            company.setName(cursor.getString(cursor.getColumnIndex("name")));
            company.setStartAt(cursor.getLong(cursor.getColumnIndex("start_at")));
            company.setLeavingAt(cursor.getLong(cursor.getColumnIndex("leaving_at")));
            company.setCreatedAt(cursor.getLong(cursor.getColumnIndex("created_at")));
            company.setUpdatedAt(cursor.getLong(cursor.getColumnIndex("updated_at")));
            all.add(company);
        }
        cursor.close();
        close();
        return all;
    }

    public boolean create(){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", getName());
        contentValues.put("start_at", getStartAt());
        contentValues.put("leaving_at", getLeavingAt());
        contentValues.put("created_at", System.currentTimeMillis());
        contentValues.put("updated_at", System.currentTimeMillis());

        db.insert(getTableName(), null, contentValues);
        close();
        return true;
    }

    public Company find(long company_id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?;";
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {String.valueOf(company_id)};

        Cursor cursor = db.rawQuery(sql, args);
        Company company = null;
        if (cursor.moveToNext()){
            company = new Company(this.context);
            company.setId(cursor.getLong(cursor.getColumnIndex("id")));
            company.setName(cursor.getString(cursor.getColumnIndex("name")));
            company.setStartAt(cursor.getLong(cursor.getColumnIndex("start_at")));
            company.setLeavingAt(cursor.getLong(cursor.getColumnIndex("leaving_at")));
            company.setCreatedAt(cursor.getLong(cursor.getColumnIndex("created_at")));
            company.setUpdatedAt(cursor.getLong(cursor.getColumnIndex("updated_at")));
        }
        cursor.close();
        close();
        return company;
    }
}
