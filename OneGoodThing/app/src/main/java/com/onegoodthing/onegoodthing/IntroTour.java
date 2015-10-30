package com.onegoodthing.onegoodthing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jackson Breyer on 10/29/2015.
 */
public class IntroTour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_tour);
    }

    //update this to open the 'add first goodthing" activity
    public void launchAddFirstGoodThing(View view){
        Intent start = new Intent(IntroTour.this, AddFirstGoodThing.class);
        IntroTour.this.startActivity(start);
    }


}

