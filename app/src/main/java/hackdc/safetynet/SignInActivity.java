package hackdc.safetynet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import javax.xml.parsers.SAXParserFactory;

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
    private TextView safety;
    private RestAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signin);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mUsername.setHintTextColor(getResources().getColor(R.color.white));
        mPassword.setHintTextColor(getResources().getColor(R.color.white));
        safety = (TextView) findViewById(R.id.safetynet);
        String first = "SAFETY";
        String next = "<font color='#FFE220'>NET</font>";
        safety.setText(Html.fromHtml(first + next));
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                login();
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
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
            }
        });
    }

    private void getId(String id){
        RestRequests requests = mAdapter.create(RestRequests.class);

        requests.getUser(id, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d("login testing", "" + user.getUsername());
                dismissProgressDialog();
                userTyper(user.getType());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void userTyper(String type){
        if(type.equals("patient")){
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        }
        else if(type.equals("support")){
            Intent support = new Intent(this, SupportActivity.class);
            startActivity(support);
        }
        else{
            Intent clinician = new Intent(this, ClinicActivity.class);
            startActivity(clinician);
        }
    }

    public void dismissProgressDialog() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

}