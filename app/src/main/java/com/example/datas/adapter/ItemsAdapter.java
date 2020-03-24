package com.example.datas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datas.activity.Details;
import com.example.datas.entity.Items;
import com.example.datas.R;

import java.util.List;

import static com.example.datas.MainActivity.cartItems;
import static com.example.datas.MainActivity.cartUpdate;
import static com.example.datas.MainActivity.itemsList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>  {

    private Context context;


    public static class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView title_item, price_item;
        ImageView img_item;
        ImageButton itemPlus;
        LinearLayout verticalLayout;

        public ItemsViewHolder(View itemView) {
            super(itemView);

            title_item = itemView.findViewById(R.id.regular_food_title);
            price_item = itemView.findViewById(R.id.regular_food_price);
            img_item = itemView.findViewById(R.id.regular_food_pc);
            itemPlus = itemView.findViewById(R.id.regular_food_plus);
            verticalLayout = itemView.findViewById(R.id.vertical_parent_layout);

        }
    }

    public ItemsAdapter(Context context, List<Items> items, int recyclerView) {
        this.context = context;
        itemsList = items;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, final int position) {
        holder.title_item.setText(itemsList.get(position).getTitle());
        holder.price_item.setText(Double.toString(itemsList.get(position).getPrice()));
        holder.img_item.setImageResource(itemsList.get(position).getImage());


        holder.itemPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems.add(itemsList.get(position));
                cartUpdate();


            }});

        holder.verticalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Title", itemsList.get(position).getTitle());
                intent.putExtra("Price", itemsList.get(position).getPrice());
                intent.putExtra("Image", itemsList.get(position).getImage());
                intent.putExtra("Size", itemsList.get(position).getSize());
                intent.putExtra("Color", itemsList.get(position).getColor());
                context.startActivity(intent);
            }
        });}


    @Override
    public int getItemCount() {
        return itemsList.size();
    }



}
