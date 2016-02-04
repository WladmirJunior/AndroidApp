package com.wlad.minutrade.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wlad on 03/02/2016.
 */
public class ClientDataBase extends SQLiteOpenHelper {

    public static final String NAME_DB = "Minutrade.db";
    public static final int VERSION_BD = 1;
    public static final String TABLE_CLIENT_NAME = "client";

    public ClientDataBase(Context context) {
        super(context, NAME_DB, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+ TABLE_CLIENT_NAME +"(cpf text PRIMARY KEY, name text not null, " +
                "email text not null, address text not null, number1 text not null, number2 text, maritalstatus text not null);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+ TABLE_CLIENT_NAME +";");
        onCreate(db);
    }
}
