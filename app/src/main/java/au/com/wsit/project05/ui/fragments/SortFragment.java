package au.com.wsit.project05.ui.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.wsit.project05.R;

/**
 * Created by guyb on 15/09/16.
 */
public class SortFragment extends DialogFragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.sort_fragment, container, false);

        return rootView;
    }
}
