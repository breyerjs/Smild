package com.onegoodthing.onegoodthing;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //if we're just starting up the app, get the feed from disk
        if (InternalStorage.theFeed == null) {
            InternalStorage.loadFeed(getApplicationContext());
        }
        loadTheFeed();

        //if there aren't any goodthings saved, begin the tour!
        if (InternalStorage.theFeed.size() == 0){
            startIntroTour();
        }
    }

    public void startAbout(View view) {
        Intent start = new Intent(MainActivity.this, About.class);
        MainActivity.this.startActivity(start);
    }

    public void startRememberWhen(View view) {
        Intent start = new Intent(MainActivity.this, RememberWhen.class);
        MainActivity.this.startActivity(start);
    }

    public void startIntroTour(){
        Intent start = new Intent(MainActivity.this, IntroTour.class);
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

    public void deleteClicked(final View view) {
        //show a dialog.
        ListView theFeed = (ListView) findViewById(R.id.the_feed);
        AddingToolkit toolkit = new AddingToolkit();
        toolkit.deleteProcess(this, view, theFeed);
    }

}

