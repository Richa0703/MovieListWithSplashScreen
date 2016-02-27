package com.example.shashankpanday.movielistassignment.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.shashankpanday.movielistassignment.Model.MovieDetails;
import com.example.shashankpanday.movielistassignment.R;
import com.example.shashankpanday.movielistassignment.Adapter.MovieListAdapter;
import com.example.shashankpanday.movielistassignment.Utils.MyApplicationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://api.androidhive.info/json/movies.json";
    ProgressDialog mPDialog;
    List<MovieDetails> movieList = new ArrayList<MovieDetails>();
    MovieListAdapter adapter;
    MyApplicationController myApplicationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // progress dialog, when list is loading
        mPDialog = new ProgressDialog(this);
        mPDialog.setMessage("Loading movie list...");
        mPDialog.show();

        adapter = new MovieListAdapter(movieList);

        // declaring recycler view
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.movieList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        // making  webservice call
        movieList();
    }

    public void movieList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("volleyResponse", response.toString());
                        mPDialog.dismiss();
                        try {
                            // parsing movie details from json response

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonMovieDetails = (JSONObject) response.get(i);
                                JSONArray genre = (JSONArray) jsonMovieDetails.getJSONArray("genre");

                                MovieDetails movieDetails = new MovieDetails();

                                String genreString = "";
                                genreString += genre.getString(0);
                                for (int j = 1; j < genre.length(); j++) {
                                    genreString += ", " + genre.getString(j);
                                }
                                movieDetails.setGenre(genreString);

                                movieDetails.setImage(jsonMovieDetails.getString("image"));

                                movieDetails.setTitle(jsonMovieDetails.getString("title"));

                                movieDetails.setRelaseYear(String.valueOf(jsonMovieDetails.getInt("releaseYear")));

                                movieDetails.setRating(String.valueOf(jsonMovieDetails.getDouble("rating")));

                                movieList.add(movieDetails);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying adapter that data has been change
                        adapter.notifyDataSetChanged();
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            // if network is slow and request is timeout then again call webservice
                            movieList();
                            mPDialog.dismiss();
                        }
                        Log.i("volleyError", error.toString());
                    }
                }

        );


        myApplicationController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}