package com.example.shashankpanday.movielistassignment.Adapter;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shashankpanday.movielistassignment.Model.MovieDetails;
import com.example.shashankpanday.movielistassignment.Activities.MoviePoster;
import com.example.shashankpanday.movielistassignment.R;
import com.example.shashankpanday.movielistassignment.Utils.MyApplicationController;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richa Pandey on 13-02-2016.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {


    List<MovieDetails> list = new ArrayList<>();

    ImageLoader imageLoader = MyApplicationController.getInstance().getImageLoader();

    public MovieListAdapter(List<MovieDetails> list) {
        this.list = list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movielist, parent, false);
        Snackbar.make(parent, "To view full movie poster click on respective movie row", Snackbar.LENGTH_LONG).show();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movieDetails = getItem(position);

        if (imageLoader == null)
            imageLoader = MyApplicationController.getInstance().getImageLoader();
        holder.networkImageView.setImageUrl(getItem(position).getImage(), imageLoader);
        holder.movieTitle.setText(list.get(position).getTitle());
        holder.movieRelaseYear.setText(list.get(position).getRelaseYear());
        holder.movieRating.setText(list.get(position).getRating());
        holder.movieGenre.setText(list.get(position).getGenre());


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public MovieDetails getItem(int i) {
        return list.get(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movieTitle;
        TextView movieGenre;
        TextView movieRating;
        TextView movieRelaseYear;
        MovieDetails movieDetails;
        NetworkImageView networkImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.movieImage);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTittle);
            movieGenre = (TextView) itemView.findViewById(R.id.movieGenere);
            movieRating = (TextView) itemView.findViewById(R.id.movieRating);
            movieRelaseYear = (TextView) itemView.findViewById(R.id.movieYear);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // gets item position
            int position = getLayoutPosition();

            // moving to another activity
            try {
                Intent intent = new Intent(view.getContext(), MoviePoster.class);

                BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.movieImage))
                        .getDrawable();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imgByte = baos.toByteArray();

                intent.putExtra("image", imgByte);
                intent.putExtra("Title", getItem(position).getTitle());
                intent.putExtra("Genre", getItem(position).getGenre());
                intent.putExtra("Rating", getItem(position).getRating());
                intent.putExtra("RelaseYear", getItem(position).getRelaseYear());
                view.getContext().startActivity(intent);

            } catch (NullPointerException e) {

                // if movie data is still loading( if network is slow) and in between user clicks on that row
                Snackbar.make(view, "Data is still loading, please wait", Snackbar.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }


}
