package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.datas.database.Database;
import com.example.datas.MainActivity;
import com.example.datas.R;
import com.google.android.material.textfield.TextInputEditText;

public class Update extends AppCompatActivity {
    Button updateUserBtn;
    TextInputEditText updatePhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //set app als fullscreen
        updateUserBtn = findViewById(R.id.UpdateUserBtn);
        updatePhone = findViewById(R.id.phoneUpdate);

        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser(){
        AsyncUpdateUser updateUserTask = new AsyncUpdateUser();

        ContentValues contentValues = new ContentValues();

        contentValues.put("phone", updatePhone.getText().toString());

        updateUserTask.execute(contentValues);
    }

    private class AsyncUpdateUser extends AsyncTask<ContentValues,Integer,String> {
        Database database;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            database = new Database(Update.this);
        }


        @Override
        protected String doInBackground(ContentValues... contentValues)
        {
            if(!database.updateCustomer(contentValues[0])){
                return "Er is iets misgegaan";
            }
            return "Gegevens successvol aangepast";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            goToPreviousActivity(RESULT_OK,s);
        }
    }
    void goToPreviousActivity(int ResultCode,String message){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("snackbarMessage", message);
        setResult(ResultCode, intent);
        finish();
    }


}
