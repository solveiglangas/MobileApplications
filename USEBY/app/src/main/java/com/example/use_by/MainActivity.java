package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button fridge = (Button) findViewById(R.id.button_refrigerator);
    Button freezer = findViewById(R.id.button_freezer);
    Button pantry = findViewById(R.id.button_pantry);
    fridge.setOnClickListener(new OnClickListener(this));
    freezer.setOnClickListener(new OnClickListener(this));
    pantry.setOnClickListener(new OnClickListener(this));

    @Override
    public void onClick(View v){
        switch(v.getId())
        {
            case R.id.fridge :
                Intent i= new Intent("com.example.OpenListActivity");
                i.putExtra("key",value);
                startActivity(i);
                break;
            case R.id.freezer :
                Intent i= new Intent("com.example.OpenListActivity");
                i.putExtra("key",value);
                startActivity(i);
                break;
            case R.id.pantry :
                Intent i= new Intent("com.example.OpenListActivity");
                i.putExtra("key",value);
                startActivity(i);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void openList(View view) {
        Intent intent = new Intent(this, OpenListActivity.class);
        intent.putExtra("list", clickedList);
        startActivity(intent);
    }


}
