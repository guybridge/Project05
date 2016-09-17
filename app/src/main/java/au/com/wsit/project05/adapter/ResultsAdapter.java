package au.com.wsit.project05.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import au.com.wsit.project05.R;
import au.com.wsit.project05.ui.fragments.MovieDetailsFragment;
import au.com.wsit.project05.utils.MovieNightConstants;
import au.com.wsit.project05.utils.ResultsItems;

/**
 * Created by guyb on 11/09/16.
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>
{
    private Context mContext;
    private ArrayList<ResultsItems> mResults;


    public ResultsAdapter(Context context, ArrayList<ResultsItems> results)
    {
        mContext = context;
        mResults = results;
    }

    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_item_view, parent, false);

        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultsViewHolder holder, final int position)
    {
        holder.bindResults(mResults.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Start Movie details Intent
                FragmentManager fm = ((Activity)mContext).getFragmentManager();
                MovieDetailsFragment movieDetails = new MovieDetailsFragment();

                // Create a bundle to store data
                Bundle bundle = new Bundle();
                bundle.putString(MovieNightConstants.POSTER_URL, mResults.get(position).getmPosterURL());
                bundle.putString(MovieNightConstants.TITLE, mResults.get(position).getmMovieTitle());
                bundle.putString(MovieNightConstants.OVERVIEW, mResults.get(position).getmOverview());

                movieDetails.setArguments(bundle);

                movieDetails.show(fm, "Movie Details");

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mResults.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mPosterImage;

        public ResultsViewHolder(View itemView)
        {
            super(itemView);

            mPosterImage = (ImageView) itemView.findViewById(R.id.moviePosterImageView);

        }

        public void bindResults(ResultsItems item)
        {
            // Picasso set image

            try
            {
                Picasso.with(mContext).load(item.getmPosterURL()).into(mPosterImage);
            }
            catch(NullPointerException e)
            {

            }


        }
    }
}
