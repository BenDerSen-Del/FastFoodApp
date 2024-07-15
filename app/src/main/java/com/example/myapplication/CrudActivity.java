package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CrudActivity extends AppCompatActivity {
    EditText edtPriceID, edtTitle, edtIdFood, edtStar, edtDescription, edtPrice, edtBestFood, edtCateID,edtImage;
    Button btnInsert, btnDelete, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        edtPrice = findViewById(R.id.edtPrice);
        edtPriceID = findViewById(R.id.edtPriceID);
        edtTitle = findViewById(R.id.edtTitle);
        edtIdFood = findViewById(R.id.edtIdFood);
        edtStar = findViewById(R.id.edtStar);
        edtDescription = findViewById(R.id.edtDescription);
        edtBestFood = findViewById(R.id.edtBestFood);
        edtCateID = findViewById(R.id.edtCateID);
        edtImage = findViewById(R.id.edtImage);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }


        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }


        });


    }

    private void DeleteData() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Ban co chac chan muon xoa khong")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Foods");
                        myRef.child("").child("").removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(CrudActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    private void UpdateData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Foods");

        Map<String,Object> map = new HashMap<>();
        map.put("BestFood","");
        map.put("CategoryId","");
        map.put("Description","");
        map.put("Id","");
        map.put("ImagePath","");
        map.put("Price","");
        map.put("PriceId","");
        map.put("Star","");
        map.put("Title","");

        myRef.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(CrudActivity.this, "Update Data Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InsertData() {
        Map<String,Object> map = new HashMap<>();
        map.put("BestFood",edtBestFood.getText().toString());
        map.put("CategoryId",edtCateID.getText().toString());
        map.put("Description",edtDescription.getText().toString());
        map.put("ImagePath",edtImage.getText().toString());
        map.put("Price",edtPrice.getText().toString());
        map.put("PriceId",edtPriceID.getText().toString());
        map.put("Star",edtStar.getText().toString());
        map.put("Title",edtTitle.getText().toString());
        map.put("Id",edtIdFood.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Food3").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CrudActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CrudActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}