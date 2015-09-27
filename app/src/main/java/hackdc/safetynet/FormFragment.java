package hackdc.safetynet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;

import hackdc.safetynet.API.RestManager;
import hackdc.safetynet.API.RestRequests;
import hackdc.safetynet.API.models.BlankResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by illipino_g on 9/26/15.
 */
public class FormFragment extends Fragment implements AdapterView.OnItemSelectedListener, Button.OnClickListener {

    private Button mSendButton,
                mDatePickerButton;

    private EditText mName,
                    mTrigger,
                    mLocation,
                    mDescription,
                    mCopingStrat;

    private static TextView mDateDescription;

    private Spinner mSeveritySpinner;

    private int mSelectedSeverity;

    private static String mDate;

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

        mDatePickerButton = (Button) view.findViewById(R.id.datePicker);
        mSendButton = (Button) view.findViewById(R.id.sendReport);
        mDateDescription = (TextView) view.findViewById(R.id.dateDescription);
        mName = (EditText) view.findViewById(R.id.etName);
        mTrigger = (EditText) view.findViewById(R.id.etTrigger);
        mSeveritySpinner = (Spinner) view.findViewById(R.id.severitySpinner);
        mLocation = (EditText) view.findViewById(R.id.etLocation);
        mDescription = (EditText) view.findViewById(R.id.etDescription);
        mCopingStrat = (EditText) view.findViewById(R.id.etCopingStrat);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.severities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSeveritySpinner.setAdapter(adapter);
        mSeveritySpinner.setOnItemSelectedListener(this);

        mDatePickerButton.setOnClickListener(this);
        mSendButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("spinner", i + "");
        mSelectedSeverity = i + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        RestAdapter restAdapter = RestManager.getRestAdapter();
        RestRequests requests = restAdapter.create(RestRequests.class);

        if(v == mSendButton) {
            HashMap report = new HashMap<>(7);
            report.put("patientname", mName.getText().toString());
            report.put("date", mDate);
            report.put("trigger", mTrigger.getText().toString());
            report.put("severity", mSelectedSeverity);
            report.put("location", mLocation.getText().toString());
            report.put("description", mDescription.getText().toString());
            report.put("copingstrat", mCopingStrat.getText().toString());

            requests.createEpisodeReport(report, new Callback<BlankResponse>() {
                @Override
                public void success(BlankResponse blankResponse, Response response) {
                    Log.d("create episode report", "successful");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("create episode report", "failure");
                }
            });
        } else if (v == mDatePickerButton) {
            DialogFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }
    }

    public static void setmDate(String d) {
        Log.d("date is being set", d);
        mDate = d;
        mDateDescription.setText(mDate);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String date = "";

            if(i1++ < 10) {
                date = "0" + i1 + "/";
            } else {
                date = i1 + "/";
            }

            if(i2 < 10) {
                date += "0" + i2 + "/";
            } else {
                date += i2 + "/";
            }

            date += i;

            FormFragment.setmDate(date);
        }
    }
}