package org.alex.stundenplan.mensa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.alex.stundenplan.R;
import org.alex.stundenplan.data.MensaDay;
import org.alex.stundenplan.helpClasses.Day;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bschramke on 21.04.15.
 */
public class MensaAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private String Tag= "Mensa Adapter";
    private final LayoutInflater inflater;
    private SparseArray<MensaDay> data = new SparseArray<>();

    public MensaAdapter(final Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getMeals().size();
    }

    @Override
    public MensaDay getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getMeals().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {



        MensaGroupView itemView = (convertView != null)
                ? (MensaGroupView) convertView
                : MensaGroupView_.build(parent.getContext());


        MensaDay group = getGroup(groupPosition);
        itemView.setText(Day.fineDate(group.getDate()));
        itemView.setChecked(isExpanded);

        //expand by default first group, because the most people a looking at it
       /* ExpandableListView eLV = (ExpandableListView) parent;
        eLV.expandGroup(0,true);*/


        return itemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Log.d(Tag, "get child view");

        final String children = getChild(groupPosition, childPosition);
        TextView text;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mensa_meal, null);
        }

        //design: set colored tabs in front of meals:
        ImageView tab = (ImageView) convertView.findViewById(R.id.tab);
        switch (childPosition){
            case 0:tab.setImageResource(R.drawable.mensa_meal_tab_0); break;
            case 1:tab.setImageResource(R.drawable.mensa_meal_tab_1); break;
            case 2:tab.setImageResource(R.drawable.mensa_meal_tab_2); break;
            case 3:tab.setImageResource(R.drawable.mensa_meal_tab_3); break;
            case 4:tab.setImageResource(R.drawable.mensa_meal_tab_4); break;
        }



        text = (TextView) convertView.findViewById(R.id.textView1);

        text.setText(Html.fromHtml(children));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * @param index
     * @return false if index is not inside data
     */
    private boolean checkDataIndexBounds(int index) {
        return (index > -1 && index < data.size());
    }

    public void updateData(List<MensaDay> items) {
        this.notifyDataSetInvalidated();
        data.clear();

        for(int i = 0; i < items.size(); i++) {
            data.put(i,items.get(i));
        }

        this.notifyDataSetChanged();
    }
}
