package au.com.wsit.project05.utils;

/**
 * Created by guyb on 6/09/16.
 */
public class MovieNightConstants
{

    public static final String API_KEY = "4a0ef3c693045b63c407d7f5b520f647";

    // API Stuff

    // Docs: https://www.themoviedb.org/documentation/api/discover
    public static final String DISCOVER_BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=4a0ef3c693045b63c407d7f5b520f647&sort_by=vote_average.desc";

    public static final String TV_BASE_URL = "https://api.themoviedb.org/3/discover/tv?api_key=4a0ef3c693045b63c407d7f5b520f647";

    // EG: https://api.themoviedb.org/3/search/movie?query=the%20matrix&api_key=4a0ef3c693045b63c407d7f5b520f647
    public static final String SEARCH_ENDPOINT = "https://api.themoviedb.org/3/search/movie?";

    // IMAGE endpoint EG https://image.tmdb.org/t/p/w500/coss7RgL0NH6g4fC2s5atvf3dFO.jpg&api_key=4a0ef3c693045b63c407d7f5b520f647
    public static final String IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w500";
    public static final String KEY_RESULTS_URL = "RESULTS_URL";

    public static final String GENRE_LIST =  "https://api.themoviedb.org/3/genre/movie/list?api_key=4a0ef3c693045b63c407d7f5b520f647";

    // JSON Keys for movies
    public static final String RESULTS = "results";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String POSTER_PATH = "poster_path";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String RELEASE_DATE = "release_date";
    public static final String MOVIE_ID = "id";

    // JSON keys for TV
    public static final String TV_TITLE = "name";


    public static final String POSTER_URL = "POSTER_URL";
    public static final String GENRES = "genres";

    public static final String KEY_SORT_POPULARITY = "popularity";
    public static final String KEY_SORT_RELEASE_DATE = "release_date";
    public static final String KEY_SORT_REVENUE = "revenue";
    public static final String KEY_SORT_AVERAGE_VOTE = "average_vote";
    public static final String KEY_SORT_NUM_VOTES = "number_of_votes";

    // Sorting
    public static final String SORT_POPULARITY = "popularity.desc";
    public static final String SORT_RELEASE_DATE = "release_date.asc";
    public static final String SORT_REVENUE = "revenue.desc";
    public static final String SORT_VOTE_AVERAGE = "vote_average.desc";
    public static final String SORT_VOTE_COUNT = "vote_count.desc";
    public static final String KEY_TV_RESULTS_URL = "KEY_TV_RESULTS_URL";
}
