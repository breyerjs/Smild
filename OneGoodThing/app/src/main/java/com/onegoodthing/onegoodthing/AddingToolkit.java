package com.onegoodthing.onegoodthing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Jackson Breyer on 11/20/2015.
 */
public class AddingToolkit {

    public AddingToolkit(){

    }

    public String saveNewGoodThing(Context context, EditText source){

        String text = source.getText().toString();

        //if they haven't entered any text
        if (text.equals(""))
            return "Please Enter Some Text";

        //if this ain't the first post
        if (Arrays.asList(context.fileList()).contains("allGoodThings")) {
            try {

                //get current max id. Use min because they compare backwards for sorting
                int currentMaxId = Collections.min(InternalStorage.theFeed, new FeedItemComparator()).gtid;

                //add it and write it back to memory
                InternalStorage.theFeed.add(new FeedItem(currentMaxId + 1, text));
                InternalStorage.sortTheFeed();
                InternalStorage.writeObject(context, "allGoodThings", InternalStorage.theFeed);

            } catch (IOException e) {
                return "IO Exception";
            }
        }

        //If it's the first post
        else{
            //make the directory and add it as the first item
            InternalStorage.theFeed.add(new FeedItem(1, text));
            try {
                InternalStorage.writeObject(context, "allGoodThings", InternalStorage.theFeed);
            } catch (IOException e) {
                return "IO Exception";
            }
        }
        return null;
    }

    public void deleteProcess(final Context context, final View view, final ListView theFeed){
    new AlertDialog.Builder(context)
            .setMessage("Are you sure you want to delete this entry?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                //if yes is clicked
                public void onClick(DialogInterface dialog, int which) {
                    performDelete(context, view, theFeed);
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                //if no is clicked
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            })
            .show();
}

    public void performDelete(Context context, View view, ListView theFeed) {
        try {
            int position = theFeed.getPositionForView(view);
            FeedItemAdapter adapter = (FeedItemAdapter) theFeed.getAdapter();
            // remove it from internal storage (save too?!?!?)
            InternalStorage.theFeed.remove(position);
            InternalStorage.writeObject(context, "allGoodThings", InternalStorage.theFeed);
            //reset the adapters stuff
            adapter.setFeedItems(InternalStorage.theFeed);
            //notify the listview
            adapter.notifyDataSetChanged();
        }catch (IOException e) {
            Log.d("IO Exception", "from deletion");
        }

    }

}

