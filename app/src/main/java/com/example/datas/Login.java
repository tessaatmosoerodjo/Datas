package com.example.datas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datas.database.Database;

public class Login extends AppCompatActivity {

    Database database;
    EditText email;
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView registerMe = findViewById(R.id.registerMe);

        registerMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });



        database = new Database(this);
        Checkforactiveuser();

    }


    public void loginEvent(View view) {

        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);

        String emailStr = String.valueOf(email.getText());
        String passwordStr = String.valueOf(password.getText());


        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Please fill in username and password",
                    Toast.LENGTH_SHORT).show();
        }

        Cursor login = database.loginValidation(email.getText().toString(), password.getText().toString());

        if (login.getCount() > 0) {

            BackgroundTask bgt = new BackgroundTask(this);
            bgt.execute("1", email.getText().toString());
            Toast.makeText(Login.this, "Welcome ;)",
                    Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(Login.this, MainActivity.class);

            if(login.moveToFirst()){
                myIntent.putExtra(MainActivity.user, login.getString(login.getColumnIndex("name")));
                myIntent.putExtra(MainActivity.email, login.getString(login.getColumnIndex("email")));
            }
            Login.this.startActivity(myIntent);
            login.close();
        } else {
            Toast.makeText(Login.this, "Username or password is NOT correct",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void Checkforactiveuser() {
        Cursor active = database.findActiveUser();
        if (active.getCount() > 0) {
            if (active.moveToFirst()) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
                email = (EditText)findViewById(R.id.login_email);
                password = (EditText)findViewById(R.id.login_password);
                email.setText(active.getString(active.getColumnIndex("email")));
                password.setText(active.getString(active.getColumnIndex("password")));
                loginEvent(layout);
            }
        }

    }
}
