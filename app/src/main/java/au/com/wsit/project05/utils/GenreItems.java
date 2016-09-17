package au.com.wsit.project05.utils;

/**
 * Created by guyb on 12/09/16.
 */
public class GenreItems
{
    public String genreName;
    public String genreID;
    public boolean isChecked;

    public boolean isChecked()
    {
        return isChecked;
    }

    public void setChecked(boolean checked)
    {
        isChecked = checked;
    }

    public String getGenreName()
    {
        return genreName;
    }

    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }

    public String getGenreID()
    {
        return genreID;
    }

    public void setGenreID(String genreID)
    {
        this.genreID = genreID;
    }
}
