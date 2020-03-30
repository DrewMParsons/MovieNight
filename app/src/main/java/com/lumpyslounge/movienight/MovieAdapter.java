package com.lumpyslounge.movienight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList)
    {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        Movie currentMovie = mMovieList.get(position);
        String imageUrl = mContext.getString(R.string.BASE_URL)+ mContext.getString(R.string.IMAGE_SIZE_185)+currentMovie.getmImageUrl();

        Picasso.get()
                .load(imageUrl)
                .fit().
                centerInside().
                into(holder.mImageView);

    }

    @Override
    public int getItemCount()
    {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;

        public MovieViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(mListener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
