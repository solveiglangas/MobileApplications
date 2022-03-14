package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import domains.Food;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-foods").allowMainThreadQueries().createFromAsset("database/foodDB.db").build();
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openList(View view) {
        Intent intent = new Intent(this, OpenListActivity.class);
        startActivity(intent);

    }

    private void fillData(){
        List<Food> foods = db.foodDao().getAllFoods();
        List<String> names = new ArrayList<>();

        for (Food f:foods){
            names.add(f.getName());
        }
        System.out.println(foods);
    }
}
