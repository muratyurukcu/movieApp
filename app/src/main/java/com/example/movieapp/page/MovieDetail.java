package com.example.movieapp.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetMovie();
    }

    private void SetMovie(){
        Movie movie = (Movie) getIntent().getSerializableExtra("Movie");
        setTitle(movie.getTitle());

        ImageView img = (ImageView) findViewById(R.id.imgDetailPage);
        Picasso.with(this).load(movie.getImgUrl()).into(img);
        TextView label;
        label= (TextView) findViewById(R.id.txtOverview);
        label.setText(movie.getOverView());
        label= (TextView) findViewById(R.id.txtPopularity);
        label.setText(movie.getPopularity());
        label= (TextView) findViewById(R.id.txtVoteAvarage);
        label.setText(movie.getVoteAverage());

    }
}