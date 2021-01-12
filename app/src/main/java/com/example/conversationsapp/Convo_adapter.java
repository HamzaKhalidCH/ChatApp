package com.example.conversationsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class Convo_adapter extends ArrayAdapter<String> {

    private ArrayList<String> convo_names = new ArrayList<>();
    Context context;

    public Convo_adapter(@NonNull Activity context, ArrayList<String> names) {
        super(context, R.layout.convo_list_item,names);
        this.context=context;
        this.convo_names=names;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        String person_name = getItem(position);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.convo_list_item,parent,false);
        }

        //listview wali line

        TextView text = (TextView) convertView.findViewById(R.id.PersonName);
        text.setText(person_name);

        return convertView;
    }
}

