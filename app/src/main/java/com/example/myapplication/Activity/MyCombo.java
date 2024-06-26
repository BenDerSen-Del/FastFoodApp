package com.example.myapplication.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyCombo extends RecyclerView.Adapter<MyCombo.MyViewHolder> {
    Context context;
    ArrayList<Combo> comboList;

    public MyCombo(Context context, ArrayList<Combo> comboList) {
        this.context = context;
        this.comboList = comboList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(comboList.get(position).getImg2()).into(holder.img2);


        Combo combo = comboList.get(position);
        holder.name2.setText(combo.getName2());
        holder.total2.setText(combo.getTotal2());
        holder.view2.setText(combo.getView2());

    }

    @Override
    public int getItemCount() {
        return comboList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name2, total2, view2;
        ImageView img2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name2 = itemView.findViewById(R.id.name2);
            total2 = itemView.findViewById(R.id.total2);
            view2 = itemView.findViewById(R.id.view2);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
