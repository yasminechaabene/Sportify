package com.chaabene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chaabene.R;
import com.chaabene.domain.Level;

import java.util.List;

public class LevelAdapter extends ArrayAdapter<Level> {

    private final Context context;
    private final int layoutResourceId;
    private final List<Level> data;
    private GroupHolder holder;
    private LayoutInflater inflater;

    public LevelAdapter(Context context, int layoutResourceId, List<Level> data)
    {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Level element = data.get(position);
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new GroupHolder();
            holder.title = (TextView)row.findViewById(R.id.level_row_title);
            holder.description = (TextView)row.findViewById(R.id.level_row_description);

            row.setTag(holder);
        }else{
            holder = (GroupHolder)row.getTag();
        }

        holder.title.setText(element.title);
        holder.description.setText(element.description);

        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getView(position,convertView,parent);
    }

    static class GroupHolder {
        TextView title;
        TextView description;
    }
}

