package com.example.datas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.datas.database.Database;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    Database database= new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void RegisterEvent(View view){


        TextInputLayout email = findViewById(R.id.text_input_reg_email);
        TextInputLayout  name = findViewById(R.id.text_input_reg_name);
        TextInputLayout password = findViewById(R.id.text_input_reg_password);
        TextInputLayout cfPassword = findViewById(R.id.text_input_reg_cfm_password);
        TextInputLayout phone = findViewById(R.id.text_input_reg_phone);
        TextInputLayout street = findViewById(R.id.text_input_reg_street);
        TextInputLayout place = findViewById(R.id.text_input_reg_place);

        Cursor result = database.emailValidation(email.getEditText().getText().toString());

        if(result.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("email", email.getEditText().getText().toString());
            values.put("password", password.getEditText().getText().toString());
            values.put("name", name.getEditText().getText().toString());
            values.put("phone", phone.getEditText().getText().toString());
            values.put("street", street.getEditText().getText().toString());
            values.put("place", place.getEditText().getText().toString());


            long insertresult = database.insertUser(values);

            if(insertresult > 0){
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, Login.class);
                startActivity(myIntent);
            }else{
                Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show();
        }
    }
}
