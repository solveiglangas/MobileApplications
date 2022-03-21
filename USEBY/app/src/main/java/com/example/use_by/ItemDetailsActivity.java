package com.example.use_by;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    // TODO: Styling
    public void openDeleteAlertDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure that you want to delete this item?");
        alertDialogBuilder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ItemDetailsActivity.this, "You clicked delete button", Toast.LENGTH_LONG).show();
                        deleteItem();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(ItemDetailsActivity.this, "You clicked cancel button", Toast.LENGTH_LONG).show();
                }
            });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void deleteItem(){
        finish();
        db.foodDao().delete(item);
        startActivity(getIntent());
    }
}