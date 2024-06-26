package com.example.myapplication.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.foodorder.databinding.ActivityDetailBinding;

public class detail_activity extends AppCompatActivity {
ActivityDetailBinding binding;
private Food object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        Glide.with(detail_activity.this)
                .load(object.getImg())
                .into(binding.imgdetail);
        binding.txtname.setText(object.getName());
        binding.txttotal.setText(object.getTotal());
        binding.txtview.setText(object.getView());

    }

    private void getIntentExtra() {
        object = (Food) getIntent().getSerializableExtra("object");
    }
}