package com.lumpyslounge.movienight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.lumpyslounge.movienight.MainActivity.EXTRA_DATE;
import static com.lumpyslounge.movienight.MainActivity.EXTRA_OVERVIEW;
import static com.lumpyslounge.movienight.MainActivity.EXTRA_RATING;
import static com.lumpyslounge.movienight.MainActivity.EXTRA_TITLE;
import static com.lumpyslounge.movienight.MainActivity.EXTRA_URL;
import static com.lumpyslounge.movienight.R.string.BASE_URL;
import static com.lumpyslounge.movienight.R.string.IMAGE_SIZE_185;


public class DetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String posterUrl = intent.getStringExtra(EXTRA_URL);
        String imageUrl = getString(BASE_URL) + getString(IMAGE_SIZE_185) + posterUrl;
        String title = intent.getStringExtra(EXTRA_TITLE);
        String overview = intent.getStringExtra(EXTRA_OVERVIEW);
        String date = intent.getStringExtra(EXTRA_DATE);
        int rating = intent.getIntExtra(EXTRA_RATING,0);

        ImageView imageView = findViewById(R.id.iv_movie_detail);
        TextView textViewTitle = findViewById(R.id.tv_title_detail);
        TextView textViewDate = findViewById(R.id.tv_date_detail);
        TextView textViewOverview = findViewById(R.id.tv_overview_detail);
        TextView textViewRating = findViewById(R.id.tv_rating_detail);

        Picasso.get().load(imageUrl)
                .fit()
                .centerInside()
                .into(imageView);
        textViewTitle.setText(title);
        textViewDate.setText(date);
        textViewOverview.setText(overview);
        textViewRating.setText(Integer.toString(rating));

    }
}
