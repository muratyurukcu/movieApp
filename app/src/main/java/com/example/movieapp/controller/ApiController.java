package com.example.movieapp.controller;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiController extends AppCompatActivity {
    String url="https://image.tmdb.org/t/p/w500/w2ezhZUk7ZJH9Mdk1Y6CTmaDRg5.jpg";
    String urlJSON="https://api.themoviedb.org/3/movie/top_rated?api_key=6c46f974d0fc4339d5c00c63f0f1c8fa&language=en-us&page=1";

    private ArrayList<Movie> movieList;
    public ArrayList<Movie> getmovieList() {
        return this.movieList;
    }
    public void setmovieList(ArrayList<Movie> list) {
        this.movieList = list;
    }
    public int urlIndx=1;
    public int pageIndx=0;

    public ApiController(){
        GetMovieList();
    }

    private void GetMovieList(){
        urlJSON = urlJSON+urlIndx;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest= new JsonObjectRequest(
                Request.Method.GET,
                urlJSON,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response : ",response.toString());

                        try{
                            Log.e("Filmin adı : " ,"Alalım");
                            JSONArray movieList= response.getJSONArray("results");
                            //SetMovieList(movieList);
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
    }

    private void SetMovieList(JSONArray list)  {
        movieList = new ArrayList<>();
        try{
            for (int i=0;i<list.length();i++){
                Movie  movie= new Movie();

                movie.setTitle(list.getJSONObject(i).getString("original_title"));
                movie.setImgUrl("https://image.tmdb.org/t/p/w500"+list.getJSONObject(i).getString("poster_path"));


                movieList.add(movie);



            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void GetNextPage(){
 /*       ArrayList<Movie> nextPageList=new ArrayList<>();

        for(int i=0;i<movieList.size();i++){

        }*/
    }
}
