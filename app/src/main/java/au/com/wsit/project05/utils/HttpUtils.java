package au.com.wsit.project05.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guyb on 11/09/16.
 */
public class HttpUtils
{
    public static final String TAG = HttpUtils.class.getSimpleName();
    public Callback mCallback;
    private String mURL;

    public HttpUtils(String url)
    {
        mURL = url;
    }

    public interface Callback
    {
        void onResponse(String data);
    }

    public void getURL(Callback callback)
    {
        mCallback = callback;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(mURL).build();

        client.newCall(request).enqueue(new okhttp3.Callback()
        {


            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                mCallback.onResponse(response.body().string());
            }
        });
    }
}
