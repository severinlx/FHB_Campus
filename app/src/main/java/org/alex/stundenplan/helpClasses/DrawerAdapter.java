package org.alex.stundenplan.helpClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.alex.stundenplan.R;

/**
 * Created by Andre on 24.03.2015.
 */
public class DrawerAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] labels;
    private final int navItem;

    public DrawerAdapter(Context context, int resource, String[] labels) {
        super(context, resource, labels);
        this.navItem = resource;
        this.context = context;
        this.labels = labels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(navItem, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(labels[position]);

        // Change icon based on name
        String s = labels[position];

        // TODO Replace hard-coded strings
        if (s.equals("Stundenplan")) {
            imageView.setImageResource(R.drawable.ic_calendar);
        } else if(s.equals("Mensa")) {
            imageView.setImageResource(R.drawable.ic_cup);
        } else if(s.equals("IQ Studentenkeller")) {
            imageView.setImageResource(R.drawable.ic_beer);
        } else if(s.equals("Bibliothek")) {
            imageView.setImageResource(R.drawable.ic_book);
        } else if(s.equals("Einstellungen")) {
            imageView.setImageResource(R.drawable.ic_action_settings);
        }

        return rowView;
    }
}
