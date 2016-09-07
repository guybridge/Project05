package au.com.wsit.project05.utils;


/**
 * Class to build the TMDB URL for Movie Night
 */
public class UrlBuilder
{
        private String mBuiltURL;

        public UrlBuilder(String baseURL)
        {
            mBuiltURL = baseURL;
        }

        public void setVoteAverageGTE(int minVoteAverage)
        {
            mBuiltURL = mBuiltURL + "&vote_average.gte=" + minVoteAverage;
        }

        public void setVoteCountGTE(int minVoteCount)
        {
            mBuiltURL = mBuiltURL + "&vote_count.gte=" + minVoteCount;
        }

        public void setReleaseDateRange(int minDate, int maxDate)
        {
            mBuiltURL = mBuiltURL + "&release_date.gte=" + minDate + "&release_date.lte=" + maxDate;
        }


        public String getmBuiltURL()
        {
            return mBuiltURL;
        }
}

