package au.com.wsit.project05.utils;

import android.util.Log;

/**
 * Class to build the TMDB URL for Movie Night
 */
public class UrlBuilder
{
    private static final String TAG = UrlBuilder.class.getSimpleName();
    private String mBuiltURL;

        public UrlBuilder(String baseURL)
        {
            mBuiltURL = baseURL;
        }

        public void setVoteAverageGTE(String minVoteAverage)
        {
            String queryParameter = "&vote_average.gte=";

            mBuiltURL = mBuiltURL + queryParameter + minVoteAverage;
        }

        public void setVoteCountGTE(String minVoteCount)
        {
            mBuiltURL = mBuiltURL + "&vote_count.gte=" + minVoteCount;
        }

        public void setReleaseDateRange(String minDate, String maxDate)
        {
            mBuiltURL = mBuiltURL + "&release_date.gte=" + minDate + "&release_date.lte=" + maxDate;
        }

        public void setGenres(String genre)
        {
                    mBuiltURL = mBuiltURL + "&with_genres=" + genre;
        }


        public String getmBuiltURL()
        {
            return mBuiltURL;
        }

        public static String replaceSortParameter(String url, String sortMethod)
        {

            String newURL = url.replace("vote_average.desc", sortMethod);
            Log.i(TAG, "New Sorted URL is: " + newURL);
            return newURL;
        }



}

