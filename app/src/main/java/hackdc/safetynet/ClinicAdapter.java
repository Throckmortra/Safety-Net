package hackdc.safetynet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hackdc.safetynet.API.models.Episodes;

/**
 * Created by aaron on 9/26/15.
 */
public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ViewHolder> {

    private List<Episodes> mEpisodes;

    public ClinicAdapter(List<Episodes> episodesList){
        mEpisodes = episodesList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSeverity;
        private TextView mDate;
        private TextView mTrigger;
        private TextView mLocation;
        private TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            mSeverity = (TextView) itemView.findViewById(R.id.txt_severity);
            mDate = (TextView) itemView.findViewById(R.id.txt_date);
            mTrigger = (TextView) itemView.findViewById(R.id.txt_trigger);
            mLocation = (TextView) itemView.findViewById(R.id.txt_location);
            mDescription = (TextView) itemView.findViewById(R.id.txt_description);
        }
    }

    @Override
    public ClinicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View menuView = inflater.inflate(R.layout.row_clinic, parent, false);
        ViewHolder viewHolder = new ViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClinicAdapter.ViewHolder holder, int position) {
        Episodes episode = mEpisodes.get(position);

        holder.mSeverity.setText(episode.getSeverity());
        holder.mDate.setText(episode.getDate());
        holder.mTrigger.setText(episode.getTrigger());
        holder.mLocation.setText(episode.getLocation());
        holder.mDescription.setText(episode.getDescription());

    }

    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }
}
