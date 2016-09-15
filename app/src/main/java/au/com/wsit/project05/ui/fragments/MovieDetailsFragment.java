package au.com.wsit.project05.ui.fragments;

import android.app.DialogFragment;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.ResultsItems;

/**
 * Created by guyb on 12/09/16.
 */
public class MovieDetailsFragment extends DialogFragment
{
    private String mPosterURL;
    private String mTitle;
    private String mOverview;

    private ImageView mPosterImageView;
    private TextView mTitleTextView;
    private TextView mOverviewTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.movie_details_fragment, container, false);

        mPosterImageView = (ImageView) rootView.findViewById(R.id.movieDetailsImageView);
        mTitleTextView = (TextView) rootView.findViewById(R.id.movieTitleTextView);
        mOverviewTextView = (TextView) rootView.findViewById(R.id.movieOverviewTextView);

        // Get the bundle
        Bundle bundle = getArguments();
        mPosterURL = bundle.getString(MovieNightConstants.POSTER_URL);
        mTitle = bundle.getString(MovieNightConstants.TITLE);
        mOverview = bundle.getString(MovieNightConstants.OVERVIEW);

        // set the data in the view
        mTitleTextView.setText(mTitle);
        mOverviewTextView.setText(mOverview);

        // Load the backdrop image
        Picasso.with(getActivity()).load(mPosterURL).into(mPosterImageView);

        return rootView;
    }
}
