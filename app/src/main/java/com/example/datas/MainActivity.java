package com.example.datas;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datas.activity.About;
import com.example.datas.activity.Account;
import com.example.datas.activity.Cart;
import com.example.datas.activity.Contact;
import com.example.datas.activity.Location;
import com.example.datas.adapter.ItemsAdapter;
import com.example.datas.entity.Items;
import com.example.datas.services.ForegoundService;
import com.example.datas.services.MyService;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String user = "username";
    public static final String email = "email@email.com";

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    public String usertext;
    public String emailtext;

    RecyclerView recyclerViewVertical;
    public static TextView tv;
    public static List<Items> cartItems = new ArrayList<>();


    public static List<Items> itemsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        usertext = intent.getStringExtra(user);
        emailtext = intent.getStringExtra(email);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        //toolbar.setLogo(R.id.logo);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //WOMEN
        itemsList.add(new Items("Floral Dress", 150, R.drawable.floral_dress, "Small", "White with floral"));
        itemsList.add(new Items("Nude dress", 95, R.drawable.dress, "Small", "Nude"));
        itemsList.add(new Items("Nude two piece", 175, R.drawable.nude_dress, "Large", "Nude"));
        itemsList.add(new Items("Blue floral Dress", 125, R.drawable.blue_dress, "Medium", "Blue/Pink"));


        itemsList.add(new Items("Baby Girl Crop Top", 125, R.drawable.black_crop, "Small", "Black"));
        itemsList.add(new Items("Girls Rule Crop Top", 90, R.drawable.grey_shirt, "Medium", "Grey"));
        itemsList.add(new Items("Amour shirt", 95, R.drawable.amour_shirt, "Small", "Pink"));

        itemsList.add(new Items("Pink bag", 95, R.drawable.pink_bag, "One Size", "Pink"));


        //MEN
        //Jeans men
        itemsList.add(new Items("Black Jeans", 200, R.drawable.black_jeans, "32", "Black"));
        itemsList.add(new Items("Blue Jeans", 225, R.drawable.blue_jeans, "34", "Blue"));

        itemsList.add(new Items("Green Shirt", 100, R.drawable.green_shirt, "Small", "Green"));
        itemsList.add(new Items("Grey Shirt", 85, R.drawable.grey_shirt_men, "Medium", "Grey"));


        recyclerViewVertical = findViewById(R.id.recyclerview);
        ItemsAdapter myAdapter = new ItemsAdapter(this, itemsList, R.id.recyclerview);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);
        recyclerViewVertical.setAdapter(myAdapter);

        cartUpdate();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        cartUpdate();
        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        tv = notifCount.findViewById(R.id.hotlist_hot);
        View view = notifCount.findViewById(R.id.hotlist_bell);

        cartUpdate();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Cart.class);
                startActivity(myIntent);
            }
        });

        return true;


    }


    public static void cartUpdate() {
        if (tv != null && cartItems != null)
            tv.setText(Integer.toString(cartItems.size()));
    }

    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.location:
                startActivity(new Intent(this, Location.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.contact:
                startActivity(new Intent(this, Contact.class));
                break;
            case R.id.notification:
                startActivity(new Intent(this, ForegoundService.class));
                break;
            case R.id.account:

                startActivity(new Intent(this, Account.class));

                break;
            case R.id.logout:
                BackgroundTask bgt = new BackgroundTask(this);
                Intent intent = getIntent();
                emailtext = intent.getStringExtra(email);
                bgt.execute("0", emailtext);
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                Toast.makeText(this,"Logged out",
                        Toast.LENGTH_SHORT).show();


                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

}
