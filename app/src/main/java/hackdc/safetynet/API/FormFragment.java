package hackdc.safetynet.API;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import hackdc.safetynet.R;

/**
 * Created by illipino_g on 9/26/15.
 */
public class FormFragment extends Fragment {

    private EditText mName,
                    mTrigger;

    private Spinner mSeveritySpinner;

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

        mName = (EditText) view.findViewById(R.id.etName);
        mTrigger = (EditText) view.findViewById(R.id.etTrigger);
        mSeveritySpinner = (Spinner) view.findViewById(R.id.severitySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.severities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSeveritySpinner.setAdapter(adapter);

        return view;
    }
}