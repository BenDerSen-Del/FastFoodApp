package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Helper.ChangeNumberItemsListener;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
private ActivityCartBinding binding;
private RecyclerView.Adapter adapter;
private ManagmentCart managmentCart;

private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        managmentCart=new ManagmentCart(this);
        
        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
           binding.emptytext.setVisibility(View.VISIBLE);
           binding.scrollviewCart.setVisibility(View.GONE);
        }
        else{
            binding.emptytext.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cartView.setLayoutManager(linearLayoutManager);
        adapter=new CartAdapter(managmentCart.getListCart(), this, () -> calculateCart());
        binding.cartView.setAdapter(adapter);
    }

    private void calculateCart() {
        double  percentTax =0.02; //tax
        double  delivery =10; //delivery

        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;
        double total=Math.round((managmentCart.getTotalFee()+tax+delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;


        binding.totalFee.setText(itemTotal + " VND");
        binding.taxFee.setText(tax + " VND");
        binding.deliveryFee.setText(delivery + " VND");
        binding.totalTxt.setText(total + " VND");
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
    }
}