package hackdc.safetynet.API;

/**
 * Created by aaron on 9/26/15.
 */

import java.util.HashMap;
import java.util.List;

import hackdc.safetynet.API.models.BlankResponse;
import hackdc.safetynet.API.models.Episodes;
import hackdc.safetynet.API.models.LoginCallback;
import hackdc.safetynet.API.models.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestRequests {

    @GET("/episodes")
    public void getAllEpisodes(Callback<List<Episodes>> response);

    @POST("/episodes")
    public void createEpisodeReport(@Body HashMap report, Callback<BlankResponse> response);

    @POST("/users/login")
    public void login(@Body HashMap credentials, Callback<LoginCallback> response);

    @GET("/users/{path}")
    public void getUser(@Path(value="path", encode=true)String ID, Callback<User> response);

    @POST("/dpdgcm")
    public void helpRequest(@Body HashMap body, Callback<BlankResponse> response);
}
