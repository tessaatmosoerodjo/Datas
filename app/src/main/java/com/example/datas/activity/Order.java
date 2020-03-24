package com.example.datas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.datas.entity.Items;
import com.example.datas.R;

import java.util.List;

import static com.example.datas.MainActivity.cartItems;

public class Order extends AppCompatActivity {

    static TextView cartPrice;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        cartPrice = findViewById(R.id.order_price);
        cartPrice.setText(Double.toString(grandTotal(cartItems)));

        priceAdjust();

        editText = findViewById(R.id.edit_text_input);
        editText.setVisibility(View.GONE);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_home_delivery:
                if (checked)
                    editText.setVisibility(View.VISIBLE);

                break;
            case R.id.radio_centrum_delivery:
                if (checked)
                    editText.setVisibility(View.GONE);
                break;
            case R.id.radio_shop:
                if (checked)
                    editText.setVisibility(View.GONE);
                break;
        }
    }

    public static int grandTotal(List<Items> items){

        int totalPrice = 0 ;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice();
        }
        return totalPrice;
    }

    public static void priceAdjust(){
        cartPrice.setText(Double.toString(grandTotal(cartItems)));
    }


    public void orderConfirmation(View view) {

        startActivity(new Intent(this, Confirmation.class));

    }
}
