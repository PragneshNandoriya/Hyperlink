package com.example.hyperlink;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String databasename = "Database" ;
    public  static  final String tablename = "UserData";
    public static int VERSION = 1;
    public static final String ID = "ID";
    public  static  final String Name = "name";
    public  static  final String Password = "password";
    public  static  final String Email = "email";
    public  static  final String Phonenum = "phonenum";
    public  static  final String Adreess = "address";
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + tablename + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name
            + " TEXT," + Password + " TEXT," + Email + " TEXT," + Phonenum + " TEXT,"+ Adreess + " TEXT)";
    public DatabaseHelper(@Nullable Context context) {
        super(context, databasename, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(sqLiteDatabase);
    }

    public void insertdata(User user){
        ContentValues values = new ContentValues();
        values.put(Name,user.getName());
        values.put(Email,user.getEmail());
        values.put(Password,user.getPassword());
        values.put(Adreess,user.getAddress());
        values.put(Phonenum,user.getPhonenumber());
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(tablename,null,values);
    }

    public boolean checkData(String email,String password){
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery(
                "select * from "+tablename +" where email = ? and password = ? ", new String[]{email, password});
       // Cursor cursor = sqldb.rawQuery("SELECT * FROM " + tablename + " WHERE "+email+"=?"+" AND "+password+ "=?", new String[]{email,password});

        // String Query = "Select * from " + tablename + " where " + Email + " = " + email +"  AND "+ Password + "="+password;
       // Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            Log.e("SQLITEOPENHELPER","Data exist");
            cursor.close();
            return false;
        }
        Log.e("SQLITEOPENHELPER","Data not exist");
        cursor.close();
        return true;
    }
}


