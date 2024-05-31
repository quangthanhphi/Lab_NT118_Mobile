package com.example.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<OS> {

    public MyAdapter(@NonNull Context context, int resource, ArrayList<OS> osArrayList) {
        super(context, R.layout.mylist, osArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.mylist, parent, false);
        }
        OS os = getItem(position);

        ImageView imageView = listItem.findViewById(R.id.imageIcon);
        TextView contents = convertView.findViewById(R.id.txtContent);
        TextView title = listItem.findViewById(R.id.txtTitle);
        title.setText(os.getTitles());
        contents.setText(os.getContents());
        imageView.setImageResource(os.getImageUrl());
        return listItem;
    }
}
