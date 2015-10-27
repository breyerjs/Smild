package com.onegoodthing.onegoodthing;
import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jackson Breyer on 9/24/2015.
 * see https://androidresearch.wordpress.com/2013/04/07/caching-objects-in-android-internal-storage/
 */
public final class InternalStorage extends AppCompatActivity{

    static ArrayList<FeedItem> theFeed;

    private InternalStorage() {}

    public static void loadFeed(Context context) {
        //To be called when app is started or when new items are added to the feed
        if (Arrays.asList(context.fileList()).contains("allGoodThings")) {
            try {
                ArrayList<GoodThing> inStorage = (ArrayList<GoodThing>) InternalStorage.readObjectList(context, "allGoodThings");
                theFeed = InternalStorage.convertGtToFeedItems(inStorage);
            } catch (ClassNotFoundException e) {
                Toast.makeText(context, "There's been an error (class)", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(context, "There's been an error (IO)", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObjectList(Context context, String key) throws IOException,
            ClassNotFoundException {
        Object output = new Object();

        FileInputStream fis = context.openFileInput(key);
        Log.d("TAG", "FIS DONE");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Log.d("TAG", "OIS CREATED");
        output = ois.readObject();
        Log.d("TAG", "LIST READ DONE");
        return output;
    }

    public Boolean fileExists(String filename) {
        return (Arrays.asList(fileList()).contains(filename));
    }

    public static ArrayList<FeedItem> convertGtToFeedItems(ArrayList<GoodThing> input){
        ArrayList<FeedItem> currentFeed = new ArrayList<FeedItem>();
        //create list of ListItems BY REVERSING ORDER OF GOODTHINGS
        // need to reverse so newest are first.
        for (int i = input.size()-1; i>=0; i--) {
            GoodThing gt = input.get(i);
            currentFeed.add(gt.convertToFeedItem());
        }
        return currentFeed;
    }
}