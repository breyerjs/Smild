package com.onegoodthing.onegoodthing;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddGoodThing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgoodthing);
    }

    public void submitPressed(View view){
        AddingToolkit toolkit = new AddingToolkit();
        EditText source = (EditText) findViewById(R.id.input_text);
        String delivered = toolkit.saveNewGoodThing(this, source);
        if (delivered==null) {
            //reload the feed, since we've added something new
            startMain();
        }
        else{
            //if they haven't entered text, ask them to!
            Toast.makeText(getApplicationContext(), delivered, Toast.LENGTH_LONG).show();
        }


    }

    public void startMain(){
        Intent start = new Intent(AddGoodThing.this, MainActivity.class);
        AddGoodThing.this.startActivity(start);
        finish(); //pretty sure this kills current activity
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
}
