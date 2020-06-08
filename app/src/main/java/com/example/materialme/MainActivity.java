package com.example.materialme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView recyclerView;
    private ArrayList<Sport> sportsData;
    private SportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the appropriate column count.
        int gridColumnCount=getResources().getInteger(R.integer.grid_column_count);

        // Initialize the RecyclerView.
        recyclerView=findViewById(R.id.recyclerView);

        //set up layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(this,gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        sportsData=new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        adapter=new SportAdapter(sportsData,this);
        recyclerView.setAdapter(adapter);

        // Get the data.
        initializeData();

        // Helper class for creating swipe to dismiss and drag and drop
        // functionality.
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        ItemTouchHelper helper= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN|ItemTouchHelper.UP
        ,swipeDirs) {



            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the
             *               original one with.
             * @return true if the item was moved, false otherwise
             */

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from=viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                // Swap the items and notify the adapter.
                Collections.swap(sportsData,from,to);
                adapter.notifyItemMoved(from,to);
                return true;
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Remove the item from the dataset.
                sportsData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });
        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(recyclerView);
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources = getResources()
                .obtainTypedArray(R.array.sports_images);

        // Clear the existing data (to avoid duplication).
        sportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            sportsData.add(new Sport(sportsList[i],sportsInfo[i],sportsImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        sportsImageResources.recycle();

        adapter.notifyDataSetChanged();
    }
    /**
     * onClick method for th FAB that resets the data.
     *
     * @param view The button view that was clicked.
     */

    public void resetSports(View view) {
        initializeData();
    }
}