package com.saiph.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tvEmpty;
    TextView tvCapital, tvPopulation, tvArea, tvRegion, tvSubregion, tvName;
    LinearLayout llDetails;

    RequestQueue requestQueue;

    String base_url = "https://restcountries.eu/rest/v2/name/";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvEmpty = (TextView) findViewById(R.id.emptyText);
        tvCapital = (TextView) findViewById(R.id.tvCapitalTxt);
        tvPopulation = (TextView) findViewById(R.id.tvPopulationTxt);
        tvArea = (TextView) findViewById(R.id.tvAreaTxt);
        tvRegion = (TextView) findViewById(R.id.tvRegionTxt);
        tvSubregion = (TextView) findViewById(R.id.tvSubregionTxt);
        tvName = (TextView) findViewById(R.id.tvCountryName);

        llDetails = (LinearLayout) findViewById(R.id.llcountry);

        requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
        getCountryDetails(getIntent().getStringExtra("country"));
    }

    private void getCountryDetails(String country) {
        //create the final url
        url = this.base_url + country + "?fullText=true";

        //create a new JsonArrayRequest. Use Volley to make HTTP request with json array response
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // attach details to the textview
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String countryName = jsonObj.get("name").toString();
                                    String capital = jsonObj.get("capital").toString();
                                    String population = jsonObj.get("population").toString();
                                    String area = jsonObj.get("area").toString();
                                    String region = jsonObj.get("region").toString();
                                    String subregion = jsonObj.get("subregion").toString();
                                    Country countryDetails = new Country(countryName, capital,
                                            population, area, region, subregion);
                                    setCountryDetails(countryDetails);
                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            //no country found
                            progressBar.setVisibility(View.GONE);
                            llDetails.setVisibility(View.GONE);
                            tvEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //no country found
                        progressBar.setVisibility(View.GONE);
                        llDetails.setVisibility(View.GONE);
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("Error");
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(req);
    }

    private void setCountryDetails(Country countryDetails) {
        progressBar.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        llDetails.setVisibility(View.VISIBLE);

        tvName.setText(countryDetails.getCountryName());
        tvCapital.setText(countryDetails.getCapital());
        tvPopulation.setText(countryDetails.getPopulation());
        tvArea.setText(countryDetails.getArea() + " sq Km.");
        tvRegion.setText(countryDetails.getRegion());
        tvSubregion.setText(countryDetails.getSubregion());
    }
}
