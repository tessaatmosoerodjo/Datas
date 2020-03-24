package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.datas.entity.Items;
import com.example.datas.R;
import com.example.datas.adapter.CartAdapter;

import java.util.List;

import static com.example.datas.MainActivity.cartItems;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    static TextView cartPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Toolbar mToolbar = findViewById(R.id.toolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.super.onBackPressed();
            }
        });

        cartPrice = findViewById(R.id.cart_price);
        cartPrice.setText(Double.toString(grandTotal(cartItems)));


        recyclerViewCart = findViewById(R.id.cart_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);
        recyclerViewCart.setNestedScrollingEnabled(false);
        recyclerViewCart.setAdapter(new CartAdapter(cartItems, R.layout.recyclerview_cart, getApplicationContext()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.order_button){
            Intent intent = new Intent(Cart.this, Order.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public static int grandTotal(List<Items> items){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice();
        }
        return totalPrice;
    }

    public static void priceAdjust(){
        cartPrice.setText(Double.toString(grandTotal(cartItems)));
    }

}


