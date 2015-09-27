package hackdc.safetynet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import hackdc.safetynet.API.RestManager;
import hackdc.safetynet.API.RestRequests;
import hackdc.safetynet.API.models.LoginCallback;
import hackdc.safetynet.API.models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by illipino_g on 9/26/15.
 */
public class SignInActivity extends FragmentActivity {

    private Button mLoginButton;
    private EditText mUsername;
    private EditText mPassword;
    private RestAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signin);

        final Intent mainActivity = new Intent(this, MainActivity.class);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                //(mainActivity);
            }
        });



    }

    private void login(){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        mAdapter = RestManager.getRestAdapter();

        RestRequests requests = mAdapter.create(RestRequests.class);

        HashMap credentials = new HashMap<>(2);
        credentials.put("username", username);
        credentials.put("password", password);

        requests.login(credentials, new Callback<LoginCallback>() {
            @Override
            public void success(LoginCallback loginCallback, Response response) {
                getId(loginCallback.getUid());
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void getId(String id){
        RestRequests requests = mAdapter.create(RestRequests.class);

        requests.getUser(id, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d("login testing", "" + user.getUsername());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


}