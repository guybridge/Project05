package au.com.wsit.project05.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TreeMap;

import au.com.wsit.project05.R;
import au.com.wsit.project05.utils.GenreItems;

/**
 * Created by guyb on 12/09/16.
 */
public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>
{
    private Context mContext;
    private GenreItems[] mGenreItems;
    private TreeMap<String, String> genresMap = new TreeMap<>();
    public static final String TAG = GenreAdapter.class.getSimpleName();


    public GenreAdapter(Context context, GenreItems[] genreItems)
    {
        mContext = context;
        mGenreItems = genreItems;

    }

    public TreeMap<String, String> getSelectedGenres()
    {
        return genresMap;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item_view, parent, false);

        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position)
    {
            holder.bindGenres(mGenreItems[position]);
    }

    @Override
    public int getItemCount()
    {
        return mGenreItems.length;
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder
    {
        private CheckBox mCheckbox;

        public GenreViewHolder(View itemView)
        {
            super(itemView);
            mCheckbox = (CheckBox) itemView.findViewById(R.id.genreCheckBox);


        }

        public void bindGenres(final GenreItems item)
        {
            Log.i(TAG, "Genre is: " + item.getGenreName());
            mCheckbox.setText(item.getGenreName());

            // Setup the button click listener
            mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if(isChecked)
                    {
                        //Toast.makeText(mContext, "Checked: Genre is: " + item.getGenreName() + "ID: " + item.getGenreID(), Toast.LENGTH_SHORT).show();
                        genresMap.put(item.getGenreID(), item.getGenreName());

                    }
                    else
                    {
                        //Toast.makeText(mContext, "Unchecked: Genre is: " + item.getGenreName() + "ID: " + item.getGenreID(), Toast.LENGTH_SHORT).show();
                        genresMap.remove(item.getGenreID());
                    }

                }
            });


        }
    }


}
