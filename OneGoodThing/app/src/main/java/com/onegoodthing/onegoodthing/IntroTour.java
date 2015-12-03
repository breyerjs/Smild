package com.onegoodthing.onegoodthing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Jackson Breyer on 10/29/2015.
 */
public class IntroTour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_tour);
        inflateTour();

    }

    public void inflateTour(){
        LinearLayout tourPane = (LinearLayout) findViewById(R.id.tour_items);
        final View tour = getLayoutInflater().inflate(R.layout.tour_items, null);
        tourPane.addView(tour);

    }

    //update this to open the 'add first goodthing" activity
    public void launchAddFirstGoodThing(View view){
        Intent start = new Intent(this, AddFirstGoodThing.class);
        this.startActivity(start);
    }


}

