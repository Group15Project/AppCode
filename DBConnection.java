package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {

    public static final String DATABASE = "UBUNTU.db";
    public static final int VERSION = 1;



    public DBConnection(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE users(Name TEXT, Surname TEXT, " +
                "Email TEXT, Phone TEXT, Province TEXT, City TEXT, Password TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE volunteers(fulname TEXT, email_address TEXT, " +
                "phone_number TEXT, address TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "volunteers");

        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String surname, String email, String phone, String province, String city, String password){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Surname", surname);
        contentValues.put("Email", email);
        contentValues.put("Phone", phone);
        contentValues.put("Province", province);
        contentValues.put("City", city);
        contentValues.put("Password", password);

        long users = database.insert("users", null, contentValues);

        if(users == -1)
            return false;
            else
                return true;

    }
    public void insertDataVolunteer(String fulname, String email_address, String phone_number, String address){

        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("Name", fulname);
        contentValues1.put("Email", email_address);
        contentValues1.put("Phone", phone_number);
        contentValues1.put("Address", address);
        data.insert("volunteers", null, contentValues1);
        }

    }

    public Boolean checkUsername(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE Email = ? ", new String[]{email});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkPassword(String email, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE Email = ? AND Password = ? ", new String[]{email, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
