package com.example.use_by;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OpenListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ListView listView;
    String location;
    List<String> foodNames;
    List<Long> foodIds;


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

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            int value = extras.getInt("key");
            System.out.println(value);
            if(value == 1){
                location = "refrigerator";
            }
            if(value == 2){
                location = "pantry";
            }
            if(value == 3){
                location = "freezer";
            }
        }

        db = AppDatabase.getInstance(getApplicationContext());
        listView = findViewById(R.id.foodList);
        fillData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillData();
    }


    private void fillData(){
        List<Food> foods = db.foodDao().findByLocation(location);

        foodIds = new ArrayList<>();
        foodNames = new ArrayList<>();

        for (Food f:foods){
            foodIds.add(f.getId());
            foodNames.add(f.getName());
        }

        CustomListAdapter adapter = new CustomListAdapter(this, foods);
        listView.setAdapter(adapter);

        // on click event on the items in ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                openItem(view, position);
            }
        });
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("list", location);
        startActivity(intent);
    }

    public void openItem(View view, int position) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        long itemId = foodIds.get(position);
        intent.putExtra("id", itemId);
        startActivity(intent);
    }


}