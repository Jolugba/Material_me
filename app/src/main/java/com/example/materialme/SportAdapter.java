package com.example.materialme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder> {

    // Member variables.
    private ArrayList<Sport> sportsData;
    private Context context;

    public SportAdapter(ArrayList<Sport> sportsData, Context context) {
        this.sportsData = sportsData;
        this.context = context;
    }

    public class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.*/

        public SportViewHolder (@NonNull View itemView) {
            super(itemView);
            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }
        void bindTo(Sport currentSport){
            // Populate the textviews with data.
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            // Load the images into the ImageView using the Glide library.
            Glide.with(context).load(
                    currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View v) {
            Sport currentSport = sportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource",
                    currentSport.getImageResource());
            context.startActivity(detailIntent);
        }
    }
    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        // Get current sport.
        Sport currentSport=sportsData.get(position);
        holder.bindTo(currentSport);
        // Populate the textviews with data.


    }

    @Override
    public int getItemCount() {
        return sportsData.size();
    }


}
