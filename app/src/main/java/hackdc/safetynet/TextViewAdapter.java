package hackdc.safetynet;

import android.content.Context;
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
    private int position;

    public TextViewAdapter(Context context, String[] textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
        this.position = position;
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
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_item, null);
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText((textViewValues[position]));
        }
        else{
            gridView = (View) convertView;
        }
        return gridView;
    }
}
