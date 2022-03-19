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
    String listLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        db = AppDatabase.getInstance(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        System.out.println(extras);
        if(extras!=null)
        {
            listLocation = extras.getString("list");
            System.out.println(listLocation+"1");
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, OpenListActivity.class);
        intent.putExtra("list",listLocation);
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
        newFood.setLocation(listLocation);
        System.out.println(newFood.getLocation()+ " location");
        System.out.println(newFood.getName() + " name");
        System.out.println(newFood.getDate()+ " date");
        System.out.println(newFood.getQuantity()+" quantity");
        System.out.println(newFood.getId()+" id");
        db.foodDao().insert(newFood);

        Intent intent = new Intent(this, OpenListActivity.class);
        intent.putExtra("list",listLocation);
        startActivity(intent);
    }
}