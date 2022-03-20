package com.example.use_by;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OpenListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ListView listView;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                fillData();
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        db = AppDatabase.getInstance(getApplicationContext());

        listView = findViewById(R.id.foodList);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                Intent intent = new Intent(view.getContext(), ItemDetailsActivity.class);
                intent.putExtra("id", id + 1); // Since autogenerated ids start in 1
                activityResultLauncher.launch(intent);
            }
        });*/

        fillData();
    }

    private void fillData(){
        List<Food> foods = db.foodDao().getAllFoods();

        List<String> foodNames = new ArrayList<>();
        for (Food f:foods){
            foodNames.add(f.getName());
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, foodNames);
        listView.setAdapter(listAdapter);
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    public void openItem(View view) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        long lId = 1;
        intent.putExtra("id", lId);
        startActivity(intent);
    }


}