package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.datas.entity.Items;
import com.example.datas.R;

import static com.example.datas.MainActivity.cartItems;
import static com.example.datas.MainActivity.cartUpdate;
import static com.example.datas.MainActivity.tv;

public class Details extends AppCompatActivity {

    private TextView tvTitle, tvPrice, tvColor, tvSize;
    private ImageView img;
    ImageButton itemDetailsPlus;

    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Details.super.onBackPressed();
                tv.setText(Integer.toString(cartItems.size()));
            }
        });

        itemDetailsPlus = findViewById(R.id.regular_food_plus_details);

        tvTitle = findViewById(R.id.food_title);
        tvPrice = findViewById(R.id.food_price);
        img = findViewById(R.id.food_image);
        tvSize = findViewById(R.id.food_size);
        tvColor = findViewById(R.id.food_color);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        double price = intent.getExtras().getDouble("Price");
        int image = intent.getExtras().getInt("Image");
        String size = intent.getExtras().getString("Size");
        String color = intent.getExtras().getString("Color");


        //setting values

        tvTitle.setText(title);
        tvPrice.setText(Double.toString(price));
        img.setImageResource(image);
        tvSize.setText(size);
        tvColor.setText(color);

        itemDetailsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems.add(items);
                tv.setText(Integer.toString(cartItems.size()));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        View view = notifCount.findViewById(R.id.hotlist_bell);

        tv = notifCount.findViewById(R.id.hotlist_hot);
        cartUpdate();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Details.this, Cart.class);
                startActivity(myIntent);

            }});

        return true;
    }

}
