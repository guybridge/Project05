package au.com.wsit.project05.utils;

/**
 * Getter Setter class for storing the movie results
 */
public class ResultsItems
{
    public String mPosterURL;
    public String mMovieID;
    public String mMovieTitle;
    public String mOverview;

    public String getmOverview()
    {
        return mOverview;
    }

    public void setmOverview(String mOverview)
    {
        this.mOverview = mOverview;
    }

    public String getmMovieID()
    {
        return mMovieID;
    }

    public void setmMovieID(String mMovieID)
    {
        this.mMovieID = mMovieID;
    }

    public String getmMovieTitle()
    {
        return mMovieTitle;
    }

    public void setmMovieTitle(String mMovieTitle)
    {
        this.mMovieTitle = mMovieTitle;
    }

    public String getmPosterURL()
    {
        return mPosterURL;
    }

    public void setmPosterURL(String mPosterURL)
    {
        this.mPosterURL = mPosterURL;
    }


}
