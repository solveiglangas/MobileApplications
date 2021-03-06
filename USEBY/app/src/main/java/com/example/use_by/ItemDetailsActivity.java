package com.example.use_by;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailsActivity extends AppCompatActivity {

    private AppDatabase db;
    long itemId;
    Food item;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        db = AppDatabase.getInstance(getApplicationContext());
        itemId = getIntent().getLongExtra("id", -1);
        item = db.foodDao().findById(itemId);
        fillItemDetails();
        setStatus();
    }

    public void fillItemDetails() {
        if (itemId !=  -1) {
            TextView title = findViewById(R.id.item_details_item_name);
            TextView quantity = findViewById(R.id.item_details_quantity);
            TextView expirationDate = findViewById(R.id.item_details_expiration_date);

            title.setText(item.getName());
            quantity.setText("Quantity: " + item.getQuantity());
            expirationDate.setText("Expiration date: "  + item.getDate());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        item = db.foodDao().findById(itemId);
        fillItemDetails();
        setStatus();
    }

    public void editItem(View view) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("id", itemId);
        System.out.println(itemId);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setStatus(){
        TextView status = findViewById(R.id.item_details_status);

        int daysToExpiration = item.getDaysUntilExpired();
        int daysToEatNow = Integer.parseInt(getResources().getString(R.string.num_days_eat_now));

        if (daysToExpiration < 0 ) {
            status.setText(getResources().getString(R.string.status_expired));
            status.setTextColor(getColor(R.color.status_expired));
        } else if (daysToExpiration <= daysToEatNow) {
            status.setText(getResources().getString(R.string.status_eat_now));
            status.setTextColor(getColor(R.color.status_eat_now));
        } else {
            status.setText(getResources().getString(R.string.status_good));
            status.setTextColor(getColor(R.color.status_good));
        }
    }

    public void openDeleteAlertDialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete "+item.getName()+"?");
        alertDialogBuilder.setMessage("Deleting this item will permanently remove it from your list.");
        alertDialogBuilder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String message = "Success! You have now deleted " + item.getName();
                        Toast.makeText(ItemDetailsActivity.this, message, Toast.LENGTH_LONG).show();
                        deleteItem();
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void deleteItem(){
        finish();
        db.foodDao().delete(item);
    }
}