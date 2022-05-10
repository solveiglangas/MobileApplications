package com.example.use_by;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddItemActivity  extends AppCompatActivity implements View.OnClickListener{

    private AppDatabase db;
    EditText input1;
    EditText input2;
    EditText input3;
    EditText selectdate;
    private int mYear, mMonth, mDay;
    String listLocation;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        db = AppDatabase.getInstance(getApplicationContext());

        selectdate = findViewById ( R.id.input2 );
        selectdate.setOnClickListener ( this );

        Bundle extras = getIntent().getExtras();
        System.out.println(extras);
        if(extras!=null)
        {
            listLocation = extras.getString("list");
            if(listLocation.equals("refrigerator")){
                itemId = 1;
            }
            if(listLocation.equals("pantry")){
                itemId = 2;
            }
            if(listLocation.equals("freezer")){
                itemId = 3;
            }
            System.out.println(listLocation+"1");
        }
    }


    public void cancel(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        selectdate = findViewById ( R.id.input2 );
        selectdate.setOnClickListener( this );
        if (v == selectdate) {
            final Calendar calendar = Calendar.getInstance ();
            mYear = calendar.get ( Calendar.YEAR );
            mMonth = calendar.get ( Calendar.MONTH );
            mDay = calendar.get ( Calendar.DAY_OF_MONTH );

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog ( this, new DatePickerDialog.OnDateSetListener () {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String dateString = String.format("%02d/%02d/%04d", dayOfMonth, (month+1), year);
                    selectdate.setText ( dateString );
                }
            }, mYear, mMonth, mDay );
            datePickerDialog.show ();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addFood(View view) throws ParseException {
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);

        Food newFood = new Food();
        newFood.setName(input1.getText().toString());
        newFood.setDate(input2.getText().toString());
        newFood.setQuantity(Integer.parseInt(input3.getText().toString()));
        newFood.setLocation(listLocation);
        db.foodDao().insert(newFood);

        finish();

    }
}