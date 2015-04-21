package org.alex.stundenplan.mensa;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.alex.stundenplan.data.MensaDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bschramke on 21.04.15.
 */
public class MensaAdapter extends BaseAdapter {

    private List<MensaDay> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MensaDay getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MensaItemView itemView = (convertView != null)
                ? (MensaItemView) convertView
                : MensaItemView_.build(parent.getContext());


        itemView.bind(getItem(position));

        return itemView;
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
        data.addAll(items);
        this.notifyDataSetChanged();
    }
}
