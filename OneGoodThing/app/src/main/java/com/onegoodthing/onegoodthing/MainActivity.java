package com.onegoodthing.onegoodthing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (InternalStorage.theFeed == null) {
            InternalStorage.loadFeed(getApplicationContext());
        }
        loadTheFeed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startAbout(View view) {
        Intent start = new Intent(MainActivity.this, About.class);
        MainActivity.this.startActivity(start);
    }

    public void startRememberWhen(View view) {
        Intent start = new Intent(MainActivity.this, RememberWhen.class);
        MainActivity.this.startActivity(start);
    }

    public void addGoodThing(View view) {
        Intent start = new Intent(MainActivity.this, AddGoodThing.class);
        MainActivity.this.startActivity(start);
    }

    public void loadTheFeed() {
        if (Arrays.asList(fileList()).contains("allGoodThings")) {

            //get the view and adapter
            ListView theFeed = (ListView) findViewById(R.id.the_feed);
            ListAdapter listAdapter = new FeedItemAdapter(this, InternalStorage.theFeed);
            //set the adapter
            theFeed.setAdapter(listAdapter);
        }

    }

}

