package com.example.learn;

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

import static com.example.learn.R.drawable.ic_launcher_foreground;


public class FruitAdpater extends ArrayAdapter {

    private int resourceId;
    private ArrayList<String>  resString;

    public FruitAdpater(Context context, int textViewResourceId, ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        resString = objects;
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView fn = view.findViewById(R.id.fruitname);
        fn.setText(resString.get(position));
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(ic_launcher_foreground);
        return view;
    }
}
