package org.alex.stundenplan.mensa;

import java.text.ParseException;
import java.util.ArrayList;

import org.alex.stundenplan.R;
import org.json.JSONException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class mensaAdapter extends BaseAdapter {

    Context context;
    ArrayList<MensaDay> mensaDayList = new ArrayList<>();


    public mensaAdapter(Context context) {

        super();
        this.context = context;
        this.mensaDayList = getMealsOnline();

    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.mensa_item, arg2, false);
        }
        //mensaDay
        MensaDay mensaDay = mensaDayList.get(arg0);

        //date view

        TextView mensaDate = (TextView) arg1.findViewById(R.id.textView_mensaDate);
        mensaDate.setText(mensaDay.date);

        //meals view: (habe ich schon vergessen jede Speise einzeln, warscheinlich hat nicht mit for schleife geklappt.. man kann es aber wieder versuchen)

        String meal = "";
        if (mensaDay.meals.size() >= 1) {
            meal = mensaDay.meals.get(0);
            TextView mensaMealA = (TextView) arg1.findViewById(R.id.textView_mensaMeal0);
            mensaMealA.setText(meal);
        }

        if (mensaDay.meals.size() >= 2) {
            meal = mensaDay.meals.get(1);
            TextView mensaMealB = (TextView) arg1.findViewById(R.id.textView_mensaMeal1);
            mensaMealB.setText(meal);
        }

        if (mensaDay.meals.size() >= 3) {
            meal = mensaDay.meals.get(2);
            TextView mensaMealC = (TextView) arg1.findViewById(R.id.textView_mensaMeal2);
            mensaMealC.setText(meal);
        }

        if (mensaDay.meals.size() >= 4) {
            meal = mensaDay.meals.get(3);
            TextView mensaMealD = (TextView) arg1.findViewById(R.id.textView_mensaMeal3);
            mensaMealD.setText(meal);
        }

        if (mensaDay.meals.size() >= 5) {
            meal = mensaDay.meals.get(4);
            TextView mensaMealE = (TextView) arg1.findViewById(R.id.textView_mensaMeal4);
            mensaMealE.setText(meal);
        }


        return arg1;
    }

    @Override
    public int getCount() {
        return mensaDayList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mensaDayList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public ArrayList<MensaDay> getMealsOnline() {

        ArrayList<MensaDay> mensaDayList;

        mensaDayList = Json.parseMeals();

        return mensaDayList;
    }
}
