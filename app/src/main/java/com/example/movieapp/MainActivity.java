package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.speech.tts.Voice;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.controller.ApiController;
import com.example.movieapp.model.Movie;
import com.example.movieapp.page.MovieDetail;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    String urlJSON="https://api.themoviedb.org/3/movie/top_rated?api_key=6c46f974d0fc4339d5c00c63f0f1c8fa&language=en-us&page=";
    public int urlIndx=1;
    public int pageIndx=0;
    private JSONArray movieListBackup;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        GetMovieList();
        final EditText txtSearch = (EditText) findViewById(R.id.txtSeachMovie);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                 // TODO Auto-generated method stub

                                                 //Value = txtSearch.getText().toString();

                                                 if (txtSearch.getText().toString().length() >= 3) {
                                                     ArrayList<Movie> tempMovieList = new ArrayList<>();
                                                     for(int i=0;i<movieList.size();i++){
                                                         if(movieList.get(i).getTitle().contains(txtSearch.getText().toString())){
                                                             tempMovieList.add(movieList.get(i));
                                                         }
                                                     }
                                                     movieList = tempMovieList;
                                                     pageIndx=0;
                                                     CleanScreen();
                                                     for (int i=0;i<movieList.size();i++){
                                                         SetScreen(movieList.get(i),i);
                                                     }
                                                     pageIndx=1;
                                                     Button btnPre = (Button) findViewById(R.id.btnPre);
                                                     btnPre.setVisibility(View.GONE);
                                                 }
                                                 else
                                                 {
                                                     CleanScreen();
                                                     movieList = new ArrayList<>();
                                                     pageIndx=0;
                                                     SetMovieList(movieListBackup);
                                                 }

                                             }
                                             @Override
                                             public void afterTextChanged(Editable editable) {

                                             }
                                         }
        );
    }

    private void GetMovieList(){
        String urlJSONPage = urlJSON+String.valueOf(urlIndx);

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest= new JsonObjectRequest(
                Request.Method.GET,
                urlJSONPage,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray ml= response.getJSONArray("results");
                            movieListBackup = ml;
                            SetMovieList(ml);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response : ",error.toString());
                    }
                }

        );

        requestQueue.add(objectRequest);
        urlIndx++;
    }

    private void SetSearchMovieList(){

    }

    private void SetMovieList(JSONArray list)  {
        try{
            for (int i=0;i<list.length();i++){
                Movie  movie= new Movie();
                movie.setTitle(list.getJSONObject(i).getString("original_title"));
                movie.setImgUrl("https://image.tmdb.org/t/p/w500"+list.getJSONObject(i).getString("poster_path"));
                movie.setOverView(list.getJSONObject(i).getString("overview"));
                movie.setPopularity(list.getJSONObject(i).getString("popularity"));
                movie.setVoteAverage(list.getJSONObject(i).getString("vote_average"));
                movieList.add(movie);
                if(pageIndx==0) {
                    SetScreen(movie,i);
                }
            }
            if(pageIndx==0) {
                pageIndx = 1;
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void SetScreen(final Movie movie, int i){
        TextView label;
        if (i == (pageIndx*6)) {
            imageView = (ImageView) findViewById(R.id.image);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label);
            label.setText(movie.getTitle());
        } else if (i == (pageIndx*6)+1) {
            imageView = (ImageView) findViewById(R.id.image2);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label2);
            label.setText(movie.getTitle());
        } else if (i == (pageIndx*6)+2) {
            imageView = (ImageView) findViewById(R.id.image3);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label3);
            label.setText(movie.getTitle());
        } else if (i == (pageIndx*6)+3) {
            imageView= (ImageView) findViewById(R.id.image4);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label4);
            label.setText(movie.getTitle());
        } else if (i == (pageIndx*6)+4) {
            imageView = (ImageView) findViewById(R.id.image5);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label5);
            label.setText(movie.getTitle());
        } else if (i == (pageIndx*6)+5) {
            imageView = (ImageView) findViewById(R.id.image6);
            Picasso.with(this).load(movie.getImgUrl()).into(imageView);
            label= (TextView) findViewById(R.id.label6);
            label.setText(movie.getTitle());
        }
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                OpenMovieDetail(movie);
            }
        });
    }

    private void OpenMovieDetail(Movie movie){
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("Movie", (Serializable) movie);
        startActivity(intent);
    }


    public void NextPage(View v){
        CleanScreen();
        Button btnPre = (Button) findViewById(R.id.btnPre);
        btnPre.setVisibility(View.VISIBLE);
        ArrayList<Movie> list = movieList;
        boolean pageI=true;
        int l_i=0;
        for(int i=pageIndx*6;i<(pageIndx+1)*6;i++) {
            l_i=i;
            if (i>=movieList.size()) {
                Button btn = (Button) findViewById(R.id.btnLoadMore);
                pageI = false;
                break;
            }
            Movie movie = list.get(i);
            SetScreen(movie,i);
        }
        if(pageI)
            pageIndx++;
        if(movieList.size()<6*pageIndx+6){
            GetMovieList();
        }
    }

    public void PreviousPage(View v){
        if (pageIndx-2<0)
            return;
        CleanScreen();
        pageIndx--;
        pageIndx--;
        for(int i=pageIndx*6;i<(pageIndx+1)*6;i++) {
            if (i>=movieList.size()) {
                Button btn = (Button) findViewById(R.id.btnLoadMore);
                break;
            }
            Movie movie = movieList.get(i);
            SetScreen(movie,i);
        }
        if(pageIndx==0) {
            Button btnPre = (Button) findViewById(R.id.btnPre);
            btnPre.setVisibility(View.GONE);
        }
        pageIndx++;
    }

    private void CleanScreen(){
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageResource(android.R.color.transparent);
        imageView = (ImageView) findViewById(R.id.image2);
        imageView.setImageResource(android.R.color.transparent);
        imageView = (ImageView) findViewById(R.id.image3);
        imageView.setImageResource(android.R.color.transparent);
        imageView = (ImageView) findViewById(R.id.image4);
        imageView.setImageResource(android.R.color.transparent);
        imageView = (ImageView) findViewById(R.id.image5);
        imageView.setImageResource(android.R.color.transparent);
        imageView = (ImageView) findViewById(R.id.image6);
        imageView.setImageResource(android.R.color.transparent);

        TextView label;
        label= (TextView) findViewById(R.id.label);
        label.setText(null);
        label= (TextView) findViewById(R.id.label2);
        label.setText(null);
        label= (TextView) findViewById(R.id.label3);
        label.setText(null);
        label= (TextView) findViewById(R.id.label4);
        label.setText(null);
        label= (TextView) findViewById(R.id.label5);
        label.setText(null);
        label= (TextView) findViewById(R.id.label6);
        label.setText(null);
    }

    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView,
                new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                    }
                });
    }

}