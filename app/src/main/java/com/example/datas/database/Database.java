package com.example.datas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datas.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DB_VERSION = 3;
    private static final String DATABASE_NAME = "hellogorgeous.db";
    private static final String TABLE_CUSTOMERS = "customer";
    private static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String STREET = "street";
    public static final String PLACE = "place";
    private static final String ACTIVE = "active";

    private static final String CREATE_TABLE_USERS = String.format(
            "create table %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(255) NOT NULL , %s VARCHAR(255) NOT NULL UNIQUE , %s VARCHAR(255) NOT NULL, %s VARCHAR(255) NOT NULL, %s VARCHAR(255) NOT NULL, %s VARCHAR(255) NOT NULL,  %s VARCHAR NOT NULL DEFAULT 0 );",
            TABLE_CUSTOMERS, ID, NAME, EMAIL, PASSWORD, PHONE, STREET, PLACE , ACTIVE);


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor loginValidation(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = String.format("%s = ? AND %s = ?", EMAIL, PASSWORD);
        String[] whereArgs = {email, password};
        Cursor cursor = db.query(TABLE_CUSTOMERS, null, whereClause, whereArgs ,null, null, null);
        return cursor;
    }

    public long insertUser(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(TABLE_CUSTOMERS, null, contentValues);
        db.close();
        //return the row ID of the newly inserted row, or -1 if an error occurred
        return rowId;
    }

    public Cursor emailValidation(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = String.format("%s = ?", EMAIL);
        String[] whereArgs = {email};
        Cursor  cursor = db.query(TABLE_CUSTOMERS, null, whereClause, whereArgs ,null, null, null);
        return cursor;

    }

    public Cursor findActiveUser() {
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = String.format("%s = ?", ACTIVE);
        String[] whereArgs = {"1"};
        Cursor cursor = db.query(TABLE_CUSTOMERS, null, whereClause, whereArgs ,null, null, null);
        return cursor;
    }

    public int active(ContentValues contentValues, String email) {
        SQLiteDatabase db = getWritableDatabase();
        int effectedRows = 0;
        String whereClause = String.format("%s = ?", EMAIL);
        String[] whereArgs = {email};
        effectedRows = db.update(TABLE_CUSTOMERS, contentValues, whereClause, whereArgs);
        return effectedRows;
    }

    public List<Customer> findCustomer(String customer_id) {
        SQLiteDatabase db = getReadableDatabase();
        //String rawquery = "SELECT * from tv_shows where id in (select tv_show_id from user_tvshow where user_id = "+user_id+");";
        String rawquery = "SELECT id,email,name,phone,street,place  from customer where id = "+customer_id+";";
        Cursor cursor = db.rawQuery(rawquery, null);
        List<Customer> recordsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String street = cursor.getString(cursor.getColumnIndex("street"));
                String place = cursor.getString(cursor.getColumnIndex("place"));


                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setEmail(email);
                customer.setPhone(phone);
                customer.setStreet(street);
                customer.setPlace(place);

                recordsList.add(customer);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }
    public boolean deleteCustomer(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int deleteQuery;
        try {
            deleteQuery = db.delete(TABLE_CUSTOMERS, "id=? ", new String[]{String.valueOf(id)});
            return deleteQuery > 0;
        } catch (
                SQLiteException e) {
            return false;
        }

    }

    public Integer deleteContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("customer","id = ?",new String[]{Integer.toString(id)});
    }


    public boolean updateCustomer(ContentValues contentValues){
        SQLiteDatabase db = getWritableDatabase();
        try {
            int updateQuery = db.update(TABLE_CUSTOMERS, contentValues, "id= ?", new String[]{String.valueOf(1)});
            return updateQuery > 0;
        }catch (SQLiteException e){
            return false;
        }
    }

}
