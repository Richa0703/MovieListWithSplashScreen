package com.example.shashankpanday.movielistassignment.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shashankpanday.movielistassignment.R;

/**
 * Created by Richa Pandey on 14-02-2016.
 */
public class MoviePoster extends AppCompatActivity {

    TextView txt_title;
    TextView txt_rating;
    TextView txt_relaseYear;
    TextView txt_genre;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_poster);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("Title");
        String rating = bundle.getString("Rating");
        String genre = bundle.getString("Genre");
        String relaseYear = bundle.getString("RelaseYear");

        byte[] b = bundle.getByteArray("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        toolbar=(Toolbar)findViewById(R.id.moviePostertoolbar);

        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView poster = (ImageView)findViewById(R.id.image);
        txt_title=(TextView)findViewById(R.id.mTitle);
        txt_genre=(TextView)findViewById(R.id.mGenre);
        txt_rating=(TextView)findViewById(R.id.mRating);
        txt_relaseYear=(TextView)findViewById(R.id.mRelase);

        poster.setImageBitmap(bmp);
        txt_title.setText(title);
        txt_genre.setText(genre);
        txt_relaseYear.setText(relaseYear);
        txt_rating.setText(rating);


    }
}
