package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OpenListActivity extends AppCompatActivity {

    private FoodAdapter dbAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        dbAdapter = new FoodAdapter(this);
        dbAdapter.open();

        lv.findViewById(R.id.foodList);
        fillData();
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    public void fillData(){
        Cursor foodCursor = dbAdapter.fetchAllFood();
        String[] from = new String[]{FoodAdapter.NAME};

        int[] to;
        SimpleCursorAdapter foods = new SimpleCursorAdapter(this, R.layout.food_row, foodCursor, from, new int[] {0});
        lv.setAdapter(foods);
    }
}