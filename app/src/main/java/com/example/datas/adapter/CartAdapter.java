package com.example.datas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datas.entity.Items;
import com.example.datas.R;

import java.util.List;

import static com.example.datas.MainActivity.cartItems;
import static com.example.datas.MainActivity.cartUpdate;
import static com.example.datas.activity.Cart.grandTotal;
import static com.example.datas.activity.Cart.priceAdjust;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context context;

    public CartAdapter(List<Items> items, int recyclerview_cart, Context context  ) {
        this.context = context;
        cartItems = items;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView cartPicture;
        TextView cartTitle, cartPrice;
        CardView cartParentLayout;
        ImageButton cartDelete;

        public CartViewHolder(View itemView) {
            super(itemView);

            cartPicture = itemView.findViewById(R.id.cart_item_pic);
            cartTitle = itemView.findViewById(R.id.cart_item_title);
            cartPrice = itemView.findViewById(R.id.cart_item_price);
            cartParentLayout = itemView.findViewById(R.id.cart_parent_layout);
            cartDelete = itemView.findViewById(R.id.cart_item_delete);


        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {

        holder.cartTitle.setText(cartItems.get(position).getTitle());
        holder.cartPrice.setText((Double.toString(cartItems.get(position).getPrice())));
        holder.cartPicture.setImageResource(cartItems.get(position).getImage());


        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items item = cartItems.get(position);
                cartItems.remove(item);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size());

                grandTotal(cartItems);
                priceAdjust();

                cartUpdate();
            }});

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

}
