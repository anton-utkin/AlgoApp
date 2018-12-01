package com.utkin.anton;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
            convertView.setTag(R.id.is_solved, convertView.findViewById(R.id.is_solved));
        }

        ((TextView)convertView.getTag(R.id.title)).setText(getItem(position).getTitle());
        final AlgorithmItem item = getItem(position);
        ((CheckBox)convertView.getTag(R.id.is_solved)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ContentValues values = new ContentValues();
                values.put(AlgorithmsProvider.NAME, item.getTitle());
                values.put(AlgorithmsProvider.IS_KNOWN, b);
                values.put(AlgorithmsProvider.CODE, item.getCode());
                getContext().getContentResolver().update(Uri.withAppendedPath(AlgorithmsProvider.CONTENT_URI, item.getId()), values,null, null);
            }
        });
        ((CheckBox)convertView.getTag(R.id.is_solved)).setChecked(getItem(position).IsKnown());
        return convertView;
    }
}
