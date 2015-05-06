package org.alex.stundenplan.mensa;

import android.content.Context;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.alex.stundenplan.R;
import org.alex.stundenplan.data.MensaDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bschramke on 21.04.15.
 */
public class MensaAdapter extends BaseExpandableListAdapter {

    private final Context context;
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
        itemView.setText(group.getDate());
        itemView.setChecked(isExpanded);

        return itemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mensa_meal, null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(Html.fromHtml(children));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, children,
                        Toast.LENGTH_SHORT).show();
            }
        });
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
