package com.example.use_by;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            int value = extras.getInt("key");
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

        initializeSearch();
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
                openItem(view, id);
            }
        });
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("list", location);
        startActivity(intent);
    }

    public void openItem(View view, long itemId) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("id", itemId);
        startActivity(intent);
    }

    private void initializeSearch() {
        SearchView searchView = (SearchView) findViewById(R.id.foodListSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                List<Food> searchedFoods = new ArrayList<Food>();

                for (Long id : foodIds) {
                    int index = foodIds.indexOf(id);
                    String foodName = foodNames.get(index);

                    if (foodName.toLowerCase().contains(s.toLowerCase())) {
                        searchedFoods.add(db.foodDao().findById(id));
                    }
                }
                System.out.println(searchedFoods);
                CustomListAdapter searchAdapter = new CustomListAdapter(listView.getContext(), searchedFoods);
                listView.setAdapter(searchAdapter);
                return false;
            }
        });
    }

}