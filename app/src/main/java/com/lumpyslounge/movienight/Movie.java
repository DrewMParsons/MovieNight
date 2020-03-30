package com.lumpyslounge.movienight;

public class Movie
{
    private String title;
    private String mImageUrl;
    private String overview;
    private int userRating;
    private String releaseDate;

    public Movie(String title, String mImageUrl, String overview, int userRating, String releaseDate)
    {
        this.title = title;
        this.mImageUrl = mImageUrl;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getmImageUrl()
    {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl)
    {
        this.mImageUrl = mImageUrl;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public int getUserRating()
    {
        return userRating;
    }

    public void setUserRating(int userRating)
    {
        this.userRating = userRating;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }
}
