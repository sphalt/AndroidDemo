package com.saiph.androiddemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<String> mCountries;
    private Context mContext;


    public CountryAdapter(List<String> countries) {
        mCountries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View countryView = inflater.inflate(R.layout.country_list_item, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(countryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Get the data model based on position
        String country = mCountries.get(i);

        // Set item views based on your views and data model
        TextView tvCountryName = viewHolder.tvCountryName;
        tvCountryName.setText(country);
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = (TextView) itemView.findViewById(R.id.country_name);
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                String country = mCountries.get(position);
                // We can access the data within the views
                Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                detailsIntent.putExtra("country", country);
                mContext.startActivity(detailsIntent);
            }
        }
    }
}
