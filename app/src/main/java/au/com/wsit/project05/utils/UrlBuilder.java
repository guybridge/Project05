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






    // Allow users to choose: rating threshold

    // vote_average.gte=

    // Allow users to choose: minimum # of ratings

    // vote_count.gte=100

    // Allow users to choose: release date range

    // release_date.gte=
    // release_date.lte=

    // https://api.themoviedb.org/3/discover/movie?sort_by=vote_count.gte=10&api_key=4a0ef3c693045b63c407d7f5b520f647

    // EXAMPLE
    // https://api.themoviedb.org/3/discover/movie? // BASE URL
    // &api_key=4a0ef3c693045b63c407d7f5b520f647    // API_KEY
    // &sort_by=
    // vote_average.desc&vote_average.gte=10
    // &vote_count.gte=100
    // &release_date.gte=2010
    // &release_date.lte=2012

    // EXAMPLE
    // https://api.themoviedb.org/3/discover/movie?&api_key=4a0ef3c693045b63c407d7f5b520f647&sort_by=vote_average.desc&vote_average.gte=5&vote_count.gte=100&release_date.gte=2010&release_date.lte=2012


}

