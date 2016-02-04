package com.wlad.minutrade.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wlad.minutrade.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wlad on 03/02/2016.
 */
public class DataBase {

    SQLiteDatabase database;

    public DataBase(Context context){
        ClientDataBase clientDataBase = new ClientDataBase(context);
        database = clientDataBase.getWritableDatabase();
    }

    public void insert(Client client){

        ContentValues values = new ContentValues();
        values.put("cpf", client.getCPF());
        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("address", client.getAddress());
        values.put("number1", client.getNumber1());
        values.put("number2", client.getNumber2());
        values.put("maritalstatus", client.getMaritalStatus());

        database.insert(ClientDataBase.TABLE_CLIENT_NAME, null, values);
    }

    public void update(Client client){

        ContentValues values = new ContentValues();
        values.put("cpf", client.getCPF());
        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("address", client.getAddress());
        values.put("number1", client.getNumber1());
        values.put("number2", client.getNumber2());
        values.put("maritalstatus", client.getMaritalStatus());

        database.update(ClientDataBase.TABLE_CLIENT_NAME, values, "cpf = " + client.getCPF(), null);
    }

    public void delete(Client client){

        database.delete(ClientDataBase.TABLE_CLIENT_NAME, "cpf = " + client.getCPF(), null);
    }

    public List<Client> retrieve(){

        List<Client> clientList = new ArrayList<Client>();
        String[] coluns = new String[]{"cpf", "name", "email", "address", "number1", "number2", "maritalstatus"};
        Cursor cursor = database.query(ClientDataBase.TABLE_CLIENT_NAME, coluns, null, null, null, null, "name ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Client client = new Client(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));

                clientList.add(client);
            }while (cursor.moveToNext());
        }

        return  clientList;
    }

    public boolean retrieve(String cpf){

        List<Client> clientList = new ArrayList<Client>();
        String[] coluns = new String[]{"cpf", "name", "email", "address", "number1", "number2", "maritalstatus"};
        Cursor cursor = database.query(ClientDataBase.TABLE_CLIENT_NAME, coluns, "cpf = "+cpf , null , null, null, "name ASC");

        if(cursor.getCount() > 0){
           return true;
        }
        return false;
    }
}
