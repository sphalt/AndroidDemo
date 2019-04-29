package com.saiph.androiddemo;

import android.content.Context;
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


    public CountryAdapter(List<String> countries) {
        mCountries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = (TextView) itemView.findViewById(R.id.country_name);
        }
    }
}
