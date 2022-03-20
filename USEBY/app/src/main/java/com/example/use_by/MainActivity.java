package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button fridge;
    Button freezer;
    Button pantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fridge = (Button) findViewById(R.id.button_refrigerator);
        freezer = (Button) findViewById(R.id.button_freezer);
        pantry = (Button) findViewById(R.id.button_pantry);

        fridge.setOnClickListener(this);
        freezer.setOnClickListener(this);
        pantry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_refrigerator: {
                Intent i = new Intent(this, OpenListActivity.class);
                i.putExtra("key",1);
                startActivity(i);
                break;
            }
            case R.id.button_pantry: {
                Intent i = new Intent(this, OpenListActivity.class);
                i.putExtra("key", 2);
                startActivity(i);
                break;
            }
            case R.id.button_freezer: {
                Intent i = new Intent(this, OpenListActivity.class);
                i.putExtra("key", 3);
                startActivity(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
