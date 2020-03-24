package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.datas.entity.Customer;
import com.example.datas.database.Database;
import com.example.datas.Login;
import com.example.datas.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Account extends AppCompatActivity {

    private static final String TAG = "Account";
    private Toolbar toolbar;
    private Button updateBtn, deleteBtn;
    private TextInputEditText dialogPassword, accountName, accountStreet, accountPhone, accountPlace;
    private TextInputLayout dialogPasswordLayout;
    private Database databaseHelper;
    public Customer customer;
    private static String user;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new Database(getApplicationContext());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);


        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);


        accountName = findViewById(R.id.accountName);
        accountPhone = findViewById(R.id.accountPhone);
        accountStreet = findViewById(R.id.accountStreet);
        accountPlace = findViewById(R.id.accountPlace);

        displayUser();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdate();
            };
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               delete();
            }
        });


    }


    public void displayUser() {

        Cursor user_id = databaseHelper.findActiveUser();
        if (user_id.moveToFirst()) {
            user = user_id.getString(user_id.getColumnIndex("id"));
            List<Customer> shows = new Database(getApplicationContext()).findCustomer(user);

            if (shows.size() > 0) {

                for (Customer customer : shows) {

                    accountName.setText(String.format("%s", customer.getName()));
                    accountPhone.setText(customer.getPhone());
                    accountStreet.setText(customer.getStreet());
                    accountPlace.setText(customer.getPlace());


                }

            }

        }

    }

    private void goToUpdate() {
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);
    }

    private void delete() {
        Cursor user_id = databaseHelper.findActiveUser();

        user = user_id.getString(user_id.getColumnIndex("id"));

//        final boolean deleteRequest =

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteContact)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        databaseHelper.deleteContact(user);
                        databaseHelper.deleteCustomer(user);
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog d = builder.create();
        d.setTitle("Are you sure ?");
        d.show();


    }

    private void deleteAccount(){
        Cursor user_id = databaseHelper.findActiveUser();

        user = user_id.getString(user_id.getColumnIndex("id"));

        final boolean deleteRequest = databaseHelper.deleteCustomer(user);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteContact)

                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (deleteRequest) {
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "Er is iets misgegaan", Toast.LENGTH_SHORT).show();
                    }
                });
        android.app.AlertDialog d = builder.create();
        d.setTitle("Are you sure ?");
        d.show();

    }





}
