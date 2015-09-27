package hackdc.safetynet.API;

import retrofit.RestAdapter;

/**
 * Created by aaron on 9/26/15.
 */
public class RestManager {
    private final static String APIURL = "http://10.29.68.100:2403";
    private static RestAdapter mRestAdapter;

    public static RestAdapter getRestAdapter() {
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(APIURL)
                    .build();
        }

        return mRestAdapter;
    }

}
