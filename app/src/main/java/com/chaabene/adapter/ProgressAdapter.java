package com.chaabene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaabene.R;
import com.chaabene.domain.HistoryRecord;
import com.chaabene.domain.TrainingHelper;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Maksym on 11/14/2014.
 */
public class ProgressAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private LayoutInflater inflater;
    private List<HistoryRecord> workouts;

    public ProgressAdapter(Context context, List<HistoryRecord> historyRecords){
        inflater = LayoutInflater.from(context);
        workouts = historyRecords;
    }

    @Override
    public int getCount() {
        return workouts.size();
    }

    @Override
    public Object getItem(int position) {
        return workouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.progress_list_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.workout_header_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "Week " + String.valueOf(workouts.get(position).week);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return workouts.get(position).week;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.progress_list_item, parent, false);
            holder.day = (TextView) convertView.findViewById(R.id.workout_day_text);
            holder.repetitions = (TextView) convertView.findViewById(R.id.workout_repetitions_text);
            holder.finishDate = (TextView) convertView.findViewById(R.id.workout_finishdate_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HistoryRecord w = workouts.get(position);
        holder.day.setText(String.format("Day %d", w.day));
        holder.repetitions.setText(String.format("%s", TrainingHelper.join(w.counts, "/")));
        holder.finishDate.setText(TrainingHelper.getDateString(w.completionTime));

        return convertView;
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView day;
        TextView repetitions;
        TextView finishDate;
    }
}
