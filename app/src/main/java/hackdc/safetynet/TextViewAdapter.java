package hackdc.safetynet;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by eakorcovelos on 9/26/15.
 */
public class TextViewAdapter extends BaseAdapter {

    private Context context;
    private final String[] textViewValues;
    private final String[] textColorValues;

    public TextViewAdapter(Context context, String[] textViewValues, String[] textColorValues) {
        this.context = context;
        this.textViewValues = textViewValues;
        this.textColorValues = textColorValues;
    }

    @Override
    public int getCount() {
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if(convertView == null){
            gridView = inflater.inflate(R.layout.grid_item, null);
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText((textViewValues[position]));
            textView.setBackgroundColor(Color.parseColor("#" + textColorValues[position]));
        }
        else{
            gridView = (View) convertView;
        }
        return gridView;
    }
}
