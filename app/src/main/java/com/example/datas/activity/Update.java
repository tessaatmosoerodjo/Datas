package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.datas.database.Database;
import com.example.datas.R;
import com.google.android.material.textfield.TextInputEditText;


public class Update extends AppCompatActivity {
    Button updateUserBtn;
    TextInputEditText updatePhone;
    Database database;
    private static String user;

    public static final String email = "email@email.com";
    public String emailtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new Database(getApplicationContext());

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

    private void updateUser() {

        String phone = updatePhone.getText().toString();

        Cursor active = database.findActiveUser();
        if (active.getCount() > 0) {
            if (active.moveToFirst()) {
                user = active.getString(active.getColumnIndex("id"));

                boolean isUpdate = database.updateData(user, phone);
                if (isUpdate == true) {
                    Toast.makeText(Update.this, "Data Update", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(Update.this, "Data not Updated", Toast.LENGTH_LONG).show();

                Intent intent = getIntent();
                emailtext = intent.getStringExtra(email);
                Intent update = new Intent(this, Account.class);
                startActivity(update);
                }
            }
        }
    }

