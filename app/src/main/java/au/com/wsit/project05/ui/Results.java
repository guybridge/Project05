package au.com.wsit.project05.ui;

import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.wsit.project05.R;
import au.com.wsit.project05.adapter.ResultsAdapter;
import au.com.wsit.project05.utils.HttpUtils;
import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.ResultsItems;

public class Results extends AppCompatActivity
{

    private static final String TAG = Results.class.getSimpleName();
    private RecyclerView mResultsRecyclerView;
    private ResultsAdapter mResultsAdapter;
    private GridLayoutManager mGridLayout;
    private ProgressBar mResultsLoadingProgress;
    private TextView mErrorTextView;

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
        mGridLayout = new GridLayoutManager(this, 2);
        mResultsRecyclerView.setLayoutManager(mGridLayout);

        // HttpUtils URL
        Intent intent = getIntent();
        String jsonURL = intent.getStringExtra(MovieNightConstants.KEY_RESULTS_URL);


        HttpUtils http = new HttpUtils(jsonURL);
        http.getURL(new HttpUtils.Callback()
        {
            @Override
            public void onResponse(String data)
            {
                try
                {
                    final ResultsItems[] resultsItems = getJSON(data);

                    if(resultsItems.length == 0)
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
                                mResultsAdapter = new ResultsAdapter(Results.this, resultsItems);
                                mResultsRecyclerView.setAdapter(mResultsAdapter);
                            }
                        });
                    }





                }
                catch (JSONException e)
                {
                    mResultsLoadingProgress.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }
        });



    }

    @NonNull
    private ResultsItems[] getJSON(String data) throws JSONException
    {
        JSONObject json = new JSONObject(data);

        JSONArray resultsArray = json.getJSONArray(MovieNightConstants.RESULTS);


            final ResultsItems[] resultsItems = new ResultsItems[resultsArray.length()];

            for (int i = 0; i < resultsArray.length(); i++)
            {
                ResultsItems items = new ResultsItems();
                // Get the poster path
                String posterPath = resultsArray.getJSONObject(i).get(MovieNightConstants.POSTER_PATH).toString();

                items.setmPosterURL(MovieNightConstants.IMAGE_ENDPOINT + posterPath + "&api_key=" + MovieNightConstants.API_KEY);

                Log.i(TAG, MovieNightConstants.IMAGE_ENDPOINT + posterPath);

                resultsItems[i] = items;
            }
            return resultsItems;
        }


    }

