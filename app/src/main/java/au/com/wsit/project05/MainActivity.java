package au.com.wsit.project05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.UrlBuilder;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new URL builder with the discover API endpoint as the base URL
        UrlBuilder builtURL = new UrlBuilder(MovieNightConstants.DISCOVER_BASE_URL);

        // Set the minimum vote average
        builtURL.setVoteAverageGTE(5);

        // Set the vote count
        builtURL.setVoteCountGTE(5);

        // Set the release date ranges
        builtURL.setReleaseDateRange(2014, 2015);

        Log.i(TAG, "The Build URL is: " + builtURL.getmBuiltURL());


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
}
