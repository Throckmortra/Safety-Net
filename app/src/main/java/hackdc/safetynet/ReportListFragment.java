package hackdc.safetynet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hackdc.safetynet.API.RestManager;
import hackdc.safetynet.API.RestRequests;
import hackdc.safetynet.API.models.Episodes;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by illipino_g on 9/27/15.
 */
public class ReportListFragment extends Fragment {

    private RestAdapter mRestAdapter;
    private RecyclerView mRecycler;
    private ClinicAdapter mRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = layoutInflater.inflate(R.layout.fragment_report_list, container, false);

        mRecycler = (RecyclerView) v.findViewById(R.id.recycler);

        mRestAdapter = RestManager.getRestAdapter();

        RestRequests requests = mRestAdapter.create(RestRequests.class);

        requests.getAllEpisodes(new Callback<List<Episodes>>() {
            @Override
            public void success(List<Episodes> episodes, Response response) {
                mRecyclerAdapter = new ClinicAdapter(episodes);
                mRecycler.setAdapter(mRecyclerAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return v;
    }
}
