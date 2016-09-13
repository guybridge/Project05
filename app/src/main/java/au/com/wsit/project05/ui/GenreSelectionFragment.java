package au.com.wsit.project05.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import au.com.wsit.project05.R;
import au.com.wsit.project05.adapter.GenreAdapter;
import au.com.wsit.project05.utils.GenreItems;
import au.com.wsit.project05.utils.HttpUtils;
import au.com.wsit.project05.utils.MovieNightConstants;

/**
 * Created by guyb on 12/09/16.
 */
public class GenreSelectionFragment extends DialogFragment
{
    private static final String TAG = GenreSelectionFragment.class.getSimpleName();
    private GenreAdapter mGenreAdapter;
    private RecyclerView mGenreReyclerView;
    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.genre_selection_fragment, container, false);

        mGenreReyclerView = (RecyclerView) rootView.findViewById(R.id.genreRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mGenreReyclerView.setLayoutManager(mLayoutManager);


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

                    final GenreItems[] genreItems = new GenreItems[genresArray.length()];

                    for (int i = 0; i < genresArray.length(); i++)
                    {
                        GenreItems items = new GenreItems();
                        Log.i(TAG, genresArray.getJSONObject(i).getString("id"));
                        Log.i(TAG, genresArray.getJSONObject(i).getString("name"));

                        items.setGenreID(genresArray.getJSONObject(i).getString("id"));
                        items.setGenreName(genresArray.getJSONObject(i).getString("name"));

                        genreItems[i] = items;

                    }

                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mGenreAdapter = new GenreAdapter(getActivity(), genreItems);
                            mGenreReyclerView.setAdapter(mGenreAdapter);
                        }
                    });
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    public TreeMap<String, String> getSelected()
    {
        return mGenreAdapter.getSelectedGenres();
    }
}
