package com.example.datas;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.example.datas.database.Database;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context context;
    public BackgroundTask(Context context) {

        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("active", strings[0]);

        Database database = new Database(context);
        database.active(contentValues, strings[1]);
        return null;
    }
}
