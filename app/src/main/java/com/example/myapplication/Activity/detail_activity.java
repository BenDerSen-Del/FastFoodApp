package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.Foods;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.databinding.ActivityDetailBinding;

public class detail_activity extends AppCompatActivity {
ActivityDetailBinding binding;
private Foods object;
private int num=1;
private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        setVariable();
    }

    private void setVariable() {

        managmentCart=(new ManagmentCart(this));

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detail_activity.this, MainActivity.class));
            }
        });
        Glide.with(detail_activity.this)
                .load(object.getImagePath())
                .into(binding.pic);
        binding.priceTxt.setText(object.getPrice()+ " Vnd");
        binding.titleTxt.setText(object.getTitle());
        binding.descTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar() + " Ratings");
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText(num*object.getPrice() + " Vnd");

        binding.plusTxt.setOnClickListener(v -> {
            num=num+1;
            binding.numTxt.setText(num+ "");
            binding.totalTxt.setText((num* object.getPrice())+ " Vnd");
        });
        binding.minusTxt.setOnClickListener(v -> {
            if(num>1){
                num = num - 1;
                binding.numTxt.setText(num+"");
                binding.totalTxt.setText((num*object.getPrice())+" Vnd");
            }
        });
        binding.addBtn.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}