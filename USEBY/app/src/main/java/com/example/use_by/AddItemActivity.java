package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    private AppDatabase db;
    EditText input1;
    EditText input2;
    EditText input3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        db = AppDatabase.getInstance(getApplicationContext());
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, OpenListActivity.class);
        startActivity(intent);
    }



    public void addFood(View view) {

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        Food newFood = new Food();
        newFood.setName(input1.getText().toString());
        newFood.setDate(input2.getText().toString());
        newFood.setQuantity(Integer.parseInt(input3.getText().toString()));
        db.foodDao().insert(newFood);


        Intent intent = new Intent(this, OpenListActivity.class);
        startActivity(intent);


    }
}