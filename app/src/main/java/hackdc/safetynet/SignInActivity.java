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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signin);

        final Intent mainActivity = new Intent(this, MainActivity.class);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mUsername = (EditText) findViewById(R.id.tliUsername);
        mPassword = (EditText) findViewById(R.id.tliPassword);

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

        RestAdapter adapter = RestManager.getRestAdapter();

        RestRequests requests = adapter.create(RestRequests.class);

        HashMap credentials = new HashMap<>(2);
        credentials.put("username", username);
        credentials.put("password", password);

        requests.login(credentials, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d("login testing", "" + user.getUsername());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("my life", "is a massive failure");
            }
        });


    }


}