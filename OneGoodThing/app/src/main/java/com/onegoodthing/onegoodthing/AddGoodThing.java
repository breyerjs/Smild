package com.onegoodthing.onegoodthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
        String delivered = addNew();
        if (delivered==null) {
            //reload the feed, since we've added something new
            InternalStorage.loadFeed(getApplicationContext());
            startMain();
        }
        else{
            //if they haven't entered text, ask them to!
            Toast.makeText(getApplicationContext(), delivered, Toast.LENGTH_LONG).show();
        }


    }

    public String addNew(){

        // see https://androidresearch.wordpress.com/2013/04/07/caching-objects-in-android-internal-storage/

        EditText mEdit   = (EditText)findViewById(R.id.input_text);
        String text = mEdit.getText().toString();

        //if they haven't entered any text
        if (text.equals(""))
            return "Please Enter Some Text";

        //if this ain't the first post
        if (Arrays.asList(fileList()).contains("allGoodThings")) {
            try {
                //get the list from memory
                ArrayList<GoodThing> currentList = (ArrayList<GoodThing>) InternalStorage.readObjectList(this, "allGoodThings");

                //get current max id
                int currentMaxId = Collections.max(currentList).gtid;

                //add it and write it back to memory
                currentList.add(new GoodThing(currentMaxId+1, text));
                InternalStorage.writeObject(this, "allGoodThings", currentList);

            } catch (IOException e) {
                return "IO Exception";
            } catch (ClassNotFoundException e) {
                return "Class not found exception";
            }
        }

        //If it's the first post
        else{
            //make the directory and add it as the first item
            ArrayList<GoodThing> currentList = new ArrayList<GoodThing>();
            currentList.add(new GoodThing(1, text));
            try {
                InternalStorage.writeObject(this, "allGoodThings", currentList);
            } catch (IOException e) {
                return "IO Exception";
            }
        }
        return null;
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
