package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Inventory.db";
    public static final String TABLE_NAME = "tbl_barang";

    public static final String COL_1 = "id_barang";
    public static final String COL_2 = "kode_barang";
    public static final String COL_3 = "nama_barang";
    public static final String COL_4 = "stok";


    private SQLiteDatabase database;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    public DataBaseHelper open() throws SQLException
    {
        database = this.getWritableDatabase();
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id_barang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kode_barang VARCHAR(50) UNIQUE, " +
                "nama_barang VARCHAR(225) NOT NULL," +
                "stok INTEGER NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String kdBarang, String nmBarang, Integer stok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, kdBarang);
        contentValues.put(COL_3, nmBarang);
        contentValues.put(COL_4, stok);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"id_barang=?",new String[]{id});
        return i;
    }

    public boolean updateData(Integer id_barang, String kd_barang, String nama_barang, Integer stok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, kd_barang);
        contentValues.put(COL_3, nama_barang);
        contentValues.put(COL_4, stok);

        int result = db.update(TABLE_NAME, contentValues,"id_barang=?", new String[]{String.valueOf(id_barang)});
        if (result>0) {
            return true;
        } else {
            return false;
        }
    }
}
