package com.example.use_by;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String PRELOADED_DATABASE_NAME = "foodDB.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDataBase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openList(View view) {
        Intent intent = new Intent(this, OpenListActivity.class);
        startActivity(intent);
    }
    private void loadDataBase(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean firstTime = prefs.getBoolean("firstTime", true);
        if (firstTime) {
            try {
                String destPath = "/data/data/" + getPackageName() +
                        "/databases/" + FoodAdapter.DATABASE_NAME;
                InputStream in = getAssets().open(PRELOADED_DATABASE_NAME);
                OutputStream out = new FileOutputStream(destPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
                out.close();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            } catch (Exception e) {
                Log.e(this.getLocalClassName(), "Exception loading database",
                        e);
            }
        }
    }


}