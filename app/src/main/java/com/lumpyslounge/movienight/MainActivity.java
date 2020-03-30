package com.lumpyslounge.movienight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener
{
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DATE = "releaseDate";
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_RATING = "userRating";
    //TODO: enter API_KEY from "themoviedb.org"
    private static final String API_KEY= "";
    private static final String POPULAR_API_KEY = "https://api.themoviedb.org/3/movie/popular?api_key=";
    private static final String TOP_RATED ="https://api.themoviedb.org/3/movie/top_rated?api_key=";

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private ArrayList<Movie> mMovieList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movie_posters);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mMovieList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON(TOP_RATED);
    }

    private void parseJSON(String sort)
    {
        String url = sort + API_KEY;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0; i< jsonArray.length(); i++){
                                JSONObject result = jsonArray.getJSONObject(i);
                                String title = result.optString("title");
                                String posterUrl = result.optString("poster_path");
                                int userRating = result.optInt("vote_average");
                                String releaseDate = result.optString("release_date");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = sdf.parse(releaseDate);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
                                String release = simpleDateFormat.format(date);
                                String overview = result.optString("overview");

                                mMovieList.add(new Movie(title,posterUrl,overview,userRating,release));
                            }
                            mMovieAdapter = new MovieAdapter(MainActivity.this,mMovieList);
                            mRecyclerView.setAdapter(mMovieAdapter);
                            mMovieAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position)
    {
        Intent intent = new Intent(this,DetailActivity.class);
        Movie clickedMovie = mMovieList.get(position);
        intent.putExtra(EXTRA_URL,clickedMovie.getmImageUrl());
        intent.putExtra(EXTRA_TITLE,clickedMovie.getTitle());
        intent.putExtra(EXTRA_DATE,clickedMovie.getReleaseDate());
        intent.putExtra(EXTRA_OVERVIEW,clickedMovie.getOverview());
        intent.putExtra(EXTRA_RATING,clickedMovie.getUserRating());

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.sort_popular:
                mMovieList = new ArrayList<>();
                mRequestQueue = Volley.newRequestQueue(this);
                parseJSON(POPULAR_API_KEY);
                return true;
            case R.id.sort_top_rated:
                mMovieList = new ArrayList<>();
                mRequestQueue = Volley.newRequestQueue(this);
                parseJSON(TOP_RATED);
                return true;
            default:
                return false;

        }

    }
}
