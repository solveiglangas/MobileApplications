package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity implements View.OnClickListener {

    private AppDatabase db;
    long itemId;
    Food item;

    EditText selectdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        System.out.println("Here");

        db = AppDatabase.getInstance(getApplicationContext());
        itemId = getIntent().getLongExtra("id", -1);
        item = db.foodDao().findById(itemId);

        selectdate = findViewById(R.id.input_date);
        selectdate.setOnClickListener(this);

        fillDefault();
    }

    public void fillDefault(){
        if (itemId != -1){
            EditText name = findViewById(R.id.input_name);
            EditText date = findViewById(R.id.input_date);
            EditText quantity = findViewById(R.id.input_quantity);

            name.setText(item.getName());
            date.setText(item.getDate());
            quantity.setText(String.valueOf(item.getQuantity()));
        }
    }

    @Override
    public void onClick(View v) {
        selectdate = findViewById (R.id.input_date);
        selectdate.setOnClickListener(this);
        if (v == selectdate) {
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener () {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    selectdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    public void edit(View view){
        EditText name = findViewById(R.id.input_name);
        EditText date = findViewById(R.id.input_date);
        EditText quantity = findViewById(R.id.input_quantity);

        item.setName(name.getText().toString());
        item.setDate(date.getText().toString());
        item.setQuantity(Integer.parseInt(quantity.getText().toString()));
        // TODO: Change list?

        db.foodDao().update(item);

        finish();
    }

    public void cancel(View view){
        finish();
    }

}