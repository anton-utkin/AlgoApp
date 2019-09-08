package com.utkin.anton;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AlgorithmsListAdapter extends ArrayAdapter<AlgorithmItem> {
    public AlgorithmsListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            Activity hostActivity = (Activity) getContext();
            convertView = hostActivity.getLayoutInflater().inflate(R.layout.listitem, null);
            convertView.setTag(R.id.title, convertView.findViewById(R.id.title));
        }

        ((TextView)convertView.getTag(R.id.title)).setText(getItem(position).getTitle());
        return convertView;
    }
}
