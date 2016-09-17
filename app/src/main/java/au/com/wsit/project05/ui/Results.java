package au.com.wsit.project05.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import au.com.wsit.project05.R;
import au.com.wsit.project05.adapter.ResultsAdapter;
import au.com.wsit.project05.ui.fragments.SortFragment;
import au.com.wsit.project05.utils.HttpUtils;
import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.ResultsItems;
import au.com.wsit.project05.utils.UrlBuilder;

public class Results extends AppCompatActivity
{

    private static final String TAG = Results.class.getSimpleName();
    private RecyclerView mResultsRecyclerView;
    private ResultsAdapter mResultsAdapter;
    private GridLayoutManager mGridLayout;
    private ProgressBar mResultsLoadingProgress;
    private TextView mErrorTextView;
    private String jsonURL;
    private String tvJsonURL;
    private Boolean showTVResults;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mErrorTextView = (TextView) findViewById(R.id.errorTextView);
        mErrorTextView.setVisibility(View.INVISIBLE);
        mResultsLoadingProgress = (ProgressBar) findViewById(R.id.progressBar);
        mResultsLoadingProgress.setVisibility(View.VISIBLE);

        mResultsRecyclerView = (RecyclerView) findViewById(R.id.resultsRecyclerView);
        mGridLayout = new GridLayoutManager(this, 3);
        mResultsRecyclerView.setLayoutManager(mGridLayout);




        // HttpUtils URL
        Intent intent = getIntent();
        jsonURL = intent.getStringExtra(MovieNightConstants.KEY_RESULTS_URL);
        tvJsonURL = intent.getStringExtra(MovieNightConstants.KEY_TV_RESULTS_URL);
        showTVResults = intent.getBooleanExtra(MovieNightConstants.KEY_SHOW_TV_RESULTS, true);

        getResults(jsonURL, tvJsonURL);


    }

    // Gets the JSON Data from the URL passed into it then loads it into the adapter
    private void getResults(String jsonURL, final String jsonUrlTV)
    {

        HttpUtils http = new HttpUtils(jsonURL);
        http.getURL(new HttpUtils.Callback()
        {
            @Override
            public void onResponse(final String movieJSONdata)
            {

                    // Once we have the movie results get the TV results
                    HttpUtils http = new HttpUtils(jsonUrlTV);
                    http.getURL(new HttpUtils.Callback()
                    {
                        @Override
                        public void onResponse(String tvJSONdata)
                        {
                            final ArrayList<ResultsItems> resultsItems = getJSON(movieJSONdata, tvJSONdata);

                            if(resultsItems.size() == 0)
                            {
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        mErrorTextView.setVisibility(View.VISIBLE);
                                        mResultsLoadingProgress.setVisibility(View.INVISIBLE);
                                    }
                                });

                            }
                            else
                            {
                                runOnUiThread(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {

                                        mResultsLoadingProgress.setVisibility(View.INVISIBLE);
                                        mResultsAdapter = new ResultsAdapter(Results.this ,resultsItems);
                                        mResultsRecyclerView.setAdapter(mResultsAdapter);
                                    }
                                });
                            }


                        }
                    });



                }

        });
    }


    // Takes the JSON results and returns a loaded getterSetter object of the results ready for loading into the adapter
    @NonNull
    private ArrayList<ResultsItems> getJSON(String movieData, String tvData)
    {
        try
        {
            JSONObject movieJSON = new JSONObject(movieData);
            JSONObject tvJSON = new JSONObject(tvData);

            JSONArray resultsArray = movieJSON.getJSONArray(MovieNightConstants.RESULTS);
            JSONArray tvresultsArray = tvJSON.getJSONArray(MovieNightConstants.RESULTS);

            //final ResultsItems[] resultsItems = new ResultsItems[resultsArray.length() + tvresultsArray.length()];
            ArrayList<ResultsItems> resultsItemsArrayList = new ArrayList<>();

            // Loop through the movie results array
            for (int i = 0; i < resultsArray.length(); i++)
            {
                ResultsItems items = new ResultsItems();
                // Get the poster path
                String posterPath = resultsArray.getJSONObject(i).get(MovieNightConstants.POSTER_PATH).toString();
                String title = resultsArray.getJSONObject(i).get(MovieNightConstants.TITLE).toString();
                String overview = resultsArray.getJSONObject(i).get(MovieNightConstants.OVERVIEW).toString();
                String movieID = resultsArray.getJSONObject(i).get(MovieNightConstants.MOVIE_ID).toString();

                items.setmPosterURL(MovieNightConstants.IMAGE_ENDPOINT + posterPath + "&api_key=" + MovieNightConstants.API_KEY);
                items.setmMovieTitle(title);
                items.setmOverview(overview);
                items.setmMovieID(movieID);

                //resultsItems[i] = items;
                // Add the items to the treeset
                resultsItemsArrayList.add(items);


            }

            if(showTVResults)
            {
                // Now get the TV results
                for (int i = 0; i < tvresultsArray.length(); i++)
                {
                    ResultsItems items = new ResultsItems();
                    // Get the poster path
                    String posterPath = tvresultsArray.getJSONObject(i).get(MovieNightConstants.POSTER_PATH).toString();
                    String title = tvresultsArray.getJSONObject(i).get(MovieNightConstants.TV_TITLE).toString();
                    String overview = tvresultsArray.getJSONObject(i).get(MovieNightConstants.OVERVIEW).toString();
                    String movieID = tvresultsArray.getJSONObject(i).get(MovieNightConstants.MOVIE_ID).toString();

                    items.setmPosterURL(MovieNightConstants.IMAGE_ENDPOINT + posterPath + "&api_key=" + MovieNightConstants.API_KEY);
                    items.setmMovieTitle(title);
                    items.setmOverview(overview);
                    items.setmMovieID(movieID);

                    //resultsItems[count] = items;
                    resultsItemsArrayList.add(items);
                }
            }


            return resultsItemsArrayList;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            return null;
        }

        }

    private boolean loadTVresults()
    {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_sort:
                sortResults();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Sort the results
    private void sortResults()
    {
        // Get access to the fragment manager
        FragmentManager fm = getFragmentManager();
        SortFragment sortFragment = new SortFragment(new SortFragment.DismissListener()
        {
            // Callback from the DialogFragment
            @Override
            public void getSelection(String selection)
            {
                Log.i(TAG, "Got a callback: " + selection);

                String sortedMovieURL;
                String sortedTVURL;
                // Get the selection and sort
                switch (selection)
                {

                    case MovieNightConstants.KEY_SORT_POPULARITY:
                        Toast.makeText(Results.this, "Sorting by popularity", Toast.LENGTH_SHORT).show();
                        sortedMovieURL = UrlBuilder.replaceSortParameter(jsonURL, MovieNightConstants.SORT_POPULARITY);
                        sortedTVURL = UrlBuilder.replaceSortParameter(tvJsonURL, MovieNightConstants.SORT_POPULARITY);
                        getResults(sortedMovieURL, sortedTVURL);
                        break;
                    case MovieNightConstants.KEY_SORT_RELEASE_DATE:
                        Toast.makeText(Results.this, "Sorting by release date", Toast.LENGTH_SHORT).show();
                        sortedMovieURL = UrlBuilder.replaceSortParameter(jsonURL, MovieNightConstants.SORT_RELEASE_DATE);
                        sortedTVURL = UrlBuilder.replaceSortParameter(tvJsonURL, MovieNightConstants.SORT_RELEASE_DATE);
                        getResults(sortedMovieURL, sortedTVURL);
                        break;
                    case MovieNightConstants.KEY_SORT_REVENUE:
                        Toast.makeText(Results.this, "Sorting by revenue", Toast.LENGTH_SHORT).show();
                        sortedMovieURL = UrlBuilder.replaceSortParameter(jsonURL, MovieNightConstants.SORT_REVENUE);
                        sortedTVURL = UrlBuilder.replaceSortParameter(tvJsonURL, MovieNightConstants.SORT_REVENUE);
                        getResults(sortedMovieURL, sortedTVURL);
                        break;
                    case MovieNightConstants.KEY_SORT_AVERAGE_VOTE:
                        Toast.makeText(Results.this, "Sorting by average vote", Toast.LENGTH_SHORT).show();
                        sortedMovieURL = UrlBuilder.replaceSortParameter(jsonURL, MovieNightConstants.SORT_VOTE_AVERAGE);
                        sortedTVURL = UrlBuilder.replaceSortParameter(tvJsonURL, MovieNightConstants.SORT_VOTE_AVERAGE);
                        getResults(sortedMovieURL, sortedTVURL);
                        break;
                    case MovieNightConstants.KEY_SORT_NUM_VOTES:
                        Toast.makeText(Results.this, "Sorting by number of votes", Toast.LENGTH_SHORT).show();
                        sortedMovieURL = UrlBuilder.replaceSortParameter(jsonURL, MovieNightConstants.KEY_SORT_NUM_VOTES);
                        sortedTVURL = UrlBuilder.replaceSortParameter(tvJsonURL, MovieNightConstants.KEY_SORT_NUM_VOTES);
                        getResults(sortedMovieURL, sortedTVURL);
                        break;
                }


            }
        });
        sortFragment.show(fm, "Sort Fragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_results, menu);

        return super.onCreateOptionsMenu(menu);
    }

}

