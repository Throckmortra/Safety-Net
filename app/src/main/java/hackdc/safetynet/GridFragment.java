package hackdc.safetynet;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Integer[] mThumbIds;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridView gridView;
    private String[] textViewValues = {"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21",
            "22", "23", "24", "25", "26", "27", "28",
            "29", "30"};
    private static String[] textColorValues = {"FF0000", "FFD900", "B2FA00", "FFD900", "FF0000", "FF0000", "FFFFFF",
            "FFFFFF", "B2FA00", "B2FA00", "B2FA00", "B2FA00", "FFD900", "FFD900",
            "B2FA00", "B2FA00", "FF0000", "FFFFFF", "FFFFFF", "FFD900", "FFFFFF",
            "B2FA00", "B2FA00", "FFD900", "FF0000", "FFFFFF", "FFD900", "B2FA00",
            "FFFFFF", "FFD900"};

    private TextViewAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridFragment newInstance(String param1, String param2) {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grid_fragment, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
        mAdapter = new TextViewAdapter(getContext(), textViewValues, textColorValues);
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.d("position thing", position + "");
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("How is your day going?");
                String[] types = {"10 - Excellent!", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0 - Terribly"};
                b.setItems(types, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                textColorValues[position] = "B2FA00";
                                MainActivity.setRank("10");
                                break;
                            case 1:
                                textColorValues[position] = "B2FA00";
                                MainActivity.setRank("9");
                                break;
                            case 2:
                                textColorValues[position] = "B2FA00";
                                MainActivity.setRank("8");
                                break;
                            case 3:
                                textColorValues[position] = "B2FA00";
                                MainActivity.setRank("7");
                                break;
                            case 4:
                                textColorValues[position] = "FFD900";
                                MainActivity.setRank("6");
                                break;
                            case 5:
                                textColorValues[position] = "FFD900";
                                MainActivity.setRank("5");
                                break;
                            case 6:
                                textColorValues[position] = "FFD900";
                                MainActivity.setRank("4");
                                break;
                            case 7:
                                textColorValues[position] = "FF0000";
                                MainActivity.setRank("3");
                                break;
                            case 8:
                                textColorValues[position] = "FF0000";
                                MainActivity.setRank("2");
                                break;
                            case 9:
                                textColorValues[position] = "FF0000";
                                MainActivity.setRank("1");
                                break;
                            case 10:
                                textColorValues[position] = "FF0000";
                                MainActivity.setRank("0");
                                break;
                        }

                        mAdapter = new TextViewAdapter(getContext(), textViewValues, textColorValues);
                        gridView.setAdapter(mAdapter);
                    }

                });

                b.show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
