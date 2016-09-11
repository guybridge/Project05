package au.com.wsit.project05.ui;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.wsit.project05.R;
import au.com.wsit.project05.adapter.ResultsAdapter;
import au.com.wsit.project05.utils.HttpUtils;
import au.com.wsit.project05.utils.MovieNightConstants;

public class Results extends AppCompatActivity
{

    private static final String TAG = Results.class.getSimpleName();
    private RecyclerView mResultsRecyclerView;
    private ResultsAdapter mResultsAdapter;
    private GridLayoutManager mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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
                    JSONObject json = new JSONObject(data);

                    JSONArray resultsArray = json.getJSONArray(MovieNightConstants.RESULTS);

                    for (int i = 0; i < resultsArray.length(); i++)
                    {
                        // Get the poster path
                        String posterPath = resultsArray.getJSONObject(i).get(MovieNightConstants.POSTER_PATH).toString();

                        Log.i(TAG, MovieNightConstants.IMAGE_ENDPOINT + posterPath);
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });



    }
}
