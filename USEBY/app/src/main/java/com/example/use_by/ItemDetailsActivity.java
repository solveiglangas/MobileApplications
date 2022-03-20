package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {

    private AppDatabase db;
    long itemId;
    Food item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        db = AppDatabase.getInstance(getApplicationContext());
        itemId = getIntent().getLongExtra("id", -1);
        item = db.foodDao().findById(itemId);
        fillItemDetails();
    }

    public void fillItemDetails() {
        db = AppDatabase.getInstance(getApplicationContext());
        long itemId = getIntent().getLongExtra("id", -1);
        Food item = db.foodDao().findById(itemId);

        if (itemId !=  -1) {
            TextView title = findViewById(R.id.item_details_item_name);
            TextView quantity = findViewById(R.id.item_details_quantity);
            TextView expirationDate = findViewById(R.id.item_details_expiration_date);


            title.setText(item.getName());
            quantity.setText("Quantity: " + String.valueOf(item.getQuantity()));
            expirationDate.setText("Expiration date: "  + item.getDate());
        }
    }

    public void editItem() {

    }

    public void deleteItem(){

    }
}