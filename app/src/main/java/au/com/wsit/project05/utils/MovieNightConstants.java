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

    // EG: https://api.themoviedb.org/3/search/movie?query=the%20matrix&api_key=4a0ef3c693045b63c407d7f5b520f647
    public static final String SEARCH_ENDPOINT = "https://api.themoviedb.org/3/search/movie?";

    // IMAGE endpoint EG https://image.tmdb.org/t/p/w500/coss7RgL0NH6g4fC2s5atvf3dFO.jpg&api_key=4a0ef3c693045b63c407d7f5b520f647
    public static final String IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w500";
    public static final String KEY_RESULTS_URL = "RESULTS_URL";

    public static final String GENRE_LIST =  "https://api.themoviedb.org/3/genre/movie/list?api_key=4a0ef3c693045b63c407d7f5b520f647";

    // JSON Keys
    public static final String RESULTS = "results";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String POSTER_PATH = "poster_path";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String RELEASE_DATE = "release_date";
    public static final String MOVIE_ID = "id";

    public static final String POSTER_URL = "POSTER_URL";
    public static final String GENRES = "genres";
}
