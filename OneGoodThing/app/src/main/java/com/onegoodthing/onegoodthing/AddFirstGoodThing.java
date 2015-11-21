package com.onegoodthing.onegoodthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddFirstGoodThing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_first_good_thing);
    }

    public void submitPressed(View view){
        AddingToolkit toolkit = new AddingToolkit();
        EditText source = (EditText) findViewById(R.id.first_goodthing_input);

        String delivered = toolkit.saveNewGoodThing(this, source);
        if (delivered==null) {
            //starting main reloads the feed
            startMain();
        }
        else{
            //if they haven't entered text, ask them to!
            Toast.makeText(getApplicationContext(), delivered, Toast.LENGTH_LONG).show();
        }


    }

    public void startMain(){
        Intent start = new Intent(AddFirstGoodThing.this, MainActivity.class);
        AddFirstGoodThing.this.startActivity(start);
        finish();
    }

}
