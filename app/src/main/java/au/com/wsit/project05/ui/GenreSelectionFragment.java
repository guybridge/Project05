package au.com.wsit.project05.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.HttpUtils;
import au.com.wsit.project05.utils.MovieNightConstants;

/**
 * Created by guyb on 12/09/16.
 */
public class GenreSelectionFragment extends DialogFragment
{
    private static final String TAG = GenreSelectionFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.genre_selection_fragment, container, false);

        // Get a list of genres
        HttpUtils httpUtils = new HttpUtils(MovieNightConstants.GENRE_LIST);
        httpUtils.getURL(new HttpUtils.Callback()
        {
            @Override
            public void onResponse(String data)
            {
                try
                {
                    JSONObject jsonData = new JSONObject(data);
                    JSONArray genresArray = jsonData.getJSONArray(MovieNightConstants.GENRES);

                    for (int i = 0; i < genresArray.length(); i++)
                    {
                        Log.i(TAG, genresArray.getJSONObject(i).getString("id"));
                        Log.i(TAG, genresArray.getJSONObject(i).getString("name"));
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }
}
