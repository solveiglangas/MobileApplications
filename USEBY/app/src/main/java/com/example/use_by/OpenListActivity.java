package com.example.use_by;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OpenListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppDatabase db;
    private ListView listView;
    String location;
    List<String> foodNames;
    List<Long> foodIds;


    private static final String[] paths = {"Good", "Eat now", "Expired"};

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onFilter(String status){
        List<Food> foods = db.foodDao().findByLocation(location);
        TextView itemStatus = (TextView) findViewById(R.id.list_item_status);
        String itemStatusValue = itemStatus.getText().toString();

        List<Food> filteredFoods = new ArrayList<Food>();
        System.out.println(itemStatusValue);

        int daysToEatNow = Integer.parseInt(getResources().getString(R.string.num_days_eat_now));
        for (Food food : foods) {
            if(status.equals("expired") && food.getDaysUntilExpired() < 0){
                filteredFoods.add(food);
            }else if(status.equals("eat now") && food.getDaysUntilExpired() <= daysToEatNow ) {
                filteredFoods.add(food);
            } else if (status.equals("good") && food.getDaysUntilExpired() > daysToEatNow ){
                filteredFoods.add(food);
            }

        }

        System.out.println(filteredFoods);

        CustomListAdapter searchAdapter = new CustomListAdapter(listView.getContext(), filteredFoods);
        listView.setAdapter(searchAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                fillData();
                break;
            case 1:
                onFilter("good");
                break;
            case 2:
                onFilter("eat now");
                break;
            case 3:
                onFilter("expired");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}