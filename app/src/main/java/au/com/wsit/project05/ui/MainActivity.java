package au.com.wsit.project05.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.UrlBuilder;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mRatingTextView;
    private SeekBar mRatingSeekBar;

    private TextView mVoteCountTextView;
    private SeekBar mVoteCountSeekBar;

    private NumberPicker mMinDatePicker;
    private NumberPicker mMaxDatePicker;

    private Button mSearchButton;

    private String mRating = String.valueOf(5);
    private String mVoteCount = String.valueOf(100);
    private String minDate;
    private String maxDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRatingTextView = (TextView) findViewById(R.id.minRatingCountTextView);
        mRatingSeekBar = (SeekBar) findViewById(R.id.ratingSeekBar);

        mVoteCountTextView = (TextView) findViewById(R.id.minVoteCountTextView);
        mVoteCountSeekBar = (SeekBar) findViewById(R.id.minVoteCountSeekBar);
        mSearchButton = (Button) findViewById(R.id.searchButton);

        mMinDatePicker = (NumberPicker) findViewById(R.id.minDate);
        mMaxDatePicker = (NumberPicker) findViewById(R.id.maxDate);

        // Seek bar setup
        mRatingSeekBar.setMax(10);
        mRatingSeekBar.setProgress(5);

        mVoteCountSeekBar.setMax(1000);
        mVoteCountSeekBar.setProgress(100);



        mRatingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mRatingTextView.setText(String.valueOf(progress));

                // Store the progress
                mRating = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        mVoteCountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                mVoteCountTextView.setText(String.valueOf(i));
                mVoteCount = String.valueOf(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // Setup the number pickers
        mMinDatePicker.setMinValue(1500);
        mMinDatePicker.setMaxValue(2050);
        mMinDatePicker.setValue(2000);
        mMinDatePicker.setWrapSelectorWheel(true);

        mMaxDatePicker.setMinValue(1500);
        mMaxDatePicker.setMaxValue(2050);
        mMaxDatePicker.setValue(2015);
        mMaxDatePicker.setWrapSelectorWheel(true);





        // Setup the button click listener
        mSearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // Get the dates
                minDate = String.valueOf(mMinDatePicker.getValue());
                maxDate = String.valueOf(mMaxDatePicker.getValue());

                String URL = createURL(mRating, mVoteCount, minDate, maxDate);

                // Start the results intent
                Intent intent = new Intent(MainActivity.this, Results.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra(MovieNightConstants.KEY_RESULTS_URL, URL);
                startActivity(intent);

            }
        });




    }

    private String createURL(String minRating, String minVoteCount, String minDate, String maxDate)
    {
        // Create a new URL builder with the discover API endpoint as the base URL
        UrlBuilder builtURL = new UrlBuilder(MovieNightConstants.DISCOVER_BASE_URL);

        // Set the minimum vote average
        builtURL.setVoteAverageGTE(minRating);

        // Set the vote count
        builtURL.setVoteCountGTE(minVoteCount);

        // Set the release date ranges
        builtURL.setReleaseDateRange(minDate, maxDate);

        Log.i(TAG, "The Build URL is: " + builtURL.getmBuiltURL());

        return builtURL.getmBuiltURL();
    }
}
