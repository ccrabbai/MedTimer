package com.example.android.medtimer;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by okwuchukwu on 4/18/2018.
 */


public class MedAdapter extends ArrayAdapter<MedicationModel> {
    public MedAdapter(Context context, int resource, List<MedicationModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_drug, parent, false);
        }

        TextView mediName = (TextView) convertView.findViewById(R.id.textview_medi_name);
        TextView dueTime = (TextView) convertView.findViewById(R.id.textview_due_time);

        MedicationModel message = getItem(position);

        mediName.setText(message.getNameOfDrug());
        dueTime.setText(message.getStartTime());

        return convertView;
    }

}
