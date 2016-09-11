package au.com.wsit.project05.adapter;

import android.app.VoiceInteractor;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.ResultsItems;

/**
 * Created by guyb on 11/09/16.
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>
{
    private Context mContext;
    private ResultsItems[] mResults;


    public ResultsAdapter(Context context, ResultsItems[] results)
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
    public void onBindViewHolder(ResultsViewHolder holder, int position)
    {
        holder.bindResults(mResults[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Start Movie details Intent
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mResults.length;
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
            Picasso.with(mContext).load(item.getmPosterURL()).into(mPosterImage);

        }
    }
}
