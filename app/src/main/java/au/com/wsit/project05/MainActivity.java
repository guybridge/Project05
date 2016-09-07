package au.com.wsit.project05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;

import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.UrlBuilder;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mRatingTextView;
    private SeekBar mRatingSeekBar;
    private EditText mVoteCountEditText;
    private Button mSearchButton;

    private DatePicker mMinDatePicker;
    private DatePicker mMaxDatePicker;

    private String mRating;
    private String mVoteCount;
    private String minDate;
    private String maxDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRatingTextView = (TextView) findViewById(R.id.minRatingCountTextView);
        mRatingSeekBar = (SeekBar) findViewById(R.id.ratingSeekBar);
        mVoteCountEditText = (EditText) findViewById(R.id.minVoteCountEditText);
        mSearchButton = (Button) findViewById(R.id.searchButton);

        mMinDatePicker = (DatePicker) findViewById(R.id.minDate);
        mMaxDatePicker = (DatePicker) findViewById(R.id.maxDate);

        // Seek bar setup
        mRatingSeekBar.setMax(10);
        mRatingSeekBar.setProgress(5);

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

        // Setup the date picker
        



        // Setup the button click listener
        mSearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get the data from the edit Text
                mVoteCount = mVoteCountEditText.getText().toString();

                // Get the dates

                createURL(mRating, mVoteCount, minDate, maxDate);
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
