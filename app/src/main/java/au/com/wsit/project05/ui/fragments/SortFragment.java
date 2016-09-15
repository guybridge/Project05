package au.com.wsit.project05.ui.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;
import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.MovieNightConstants;

/**
 * Created by guyb on 15/09/16.
 */
public class SortFragment extends DialogFragment
{

    private RadioGroup sortGroup;
    private String mSelection;
    private DismissListener mListener;

    public interface DismissListener
    {
        void getSelection(String selection);
    }

    public SortFragment(DismissListener listener)
    {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.sort_fragment, container, false);

        sortGroup = (RadioGroup) rootView.findViewById(R.id.sortRadioGroup);
        sortGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID)
            {
                switch (checkedID)
                {
                    case R.id.radioPopularity:
                        mSelection = MovieNightConstants.KEY_SORT_POPULARITY;

                        break;
                    case R.id.radioReleaseDate:
                        mSelection = MovieNightConstants.KEY_SORT_RELEASE_DATE;
                        Toast.makeText(getActivity(), "Sorting by release date", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioRevenue:
                        mSelection = MovieNightConstants.KEY_SORT_REVENUE;
                        Toast.makeText(getActivity(), "Sorting by revenue", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioAverageVote:
                        mSelection = MovieNightConstants.KEY_SORT_AVERAGE_VOTE;
                        Toast.makeText(getActivity(), "Sorting by average vote", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioNumVotes:
                        mSelection = MovieNightConstants.KEY_SORT_NUM_VOTES;
                        Toast.makeText(getActivity(), "Sorting by number of votes", Toast.LENGTH_SHORT).show();
                        break;
                }

                mListener.getSelection(mSelection);
                closeFragment();
            }
        });

        return rootView;
    }

    private void closeFragment()
    {
        getDialog().cancel();
    }
}
