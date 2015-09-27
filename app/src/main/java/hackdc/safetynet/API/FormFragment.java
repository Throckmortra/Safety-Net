package hackdc.safetynet.API;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hackdc.safetynet.R;

/**
 * Created by illipino_g on 9/26/15.
 */
public class FormFragment extends Fragment {

    public static FormFragment newInstance() {
        FormFragment fragment = new FormFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_form, container, false);

        return view;
    }
}