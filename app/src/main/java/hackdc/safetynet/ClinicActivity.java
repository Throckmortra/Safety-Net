package hackdc.safetynet;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.List;

import hackdc.safetynet.API.RestManager;
import hackdc.safetynet.API.RestRequests;
import hackdc.safetynet.API.models.Episodes;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by aaron on 9/26/15.
 */
public class ClinicActivity extends FragmentActivity {

    private ProgressDialog mProgressDialog;
    private RecyclerView mRecycler;
    private ClinicAdapter mRecyclerAdapter;
    private RestAdapter mRestAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clinic);

        showProgressDialog();
        mRestAdapter = RestManager.getRestAdapter();

        RestRequests requests = mRestAdapter.create(RestRequests.class);

        requests.getAllEpisodes(new Callback<List<Episodes>>() {
            @Override
            public void success(List<Episodes> episodes, Response response) {
                mRecycler = (RecyclerView) findViewById(R.id.recycler);
                mRecyclerAdapter = new ClinicAdapter(episodes);
                mRecycler.setAdapter(mRecyclerAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                dismissProgressDialog();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

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
