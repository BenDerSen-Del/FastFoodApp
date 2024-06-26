package com.example.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Food> list;

    public MyAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.main_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg()).into(holder.img);


        Food food = list.get(position);
        holder.name.setText(food.getName());
        holder.total.setText(food.getTotal());
        holder.view.setText(food.getView());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,detail_activity.class);
            intent.putExtra("object",list.get(position));
            context.startActivity(intent);
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, total,view;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name1);
            total = itemView.findViewById(R.id.total1);
            view = itemView.findViewById(R.id.view1);
            img = itemView.findViewById(R.id.img1);

        }
    }

}
