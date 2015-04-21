package org.alex.stundenplan.mensa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.alex.stundenplan.R;
import org.alex.stundenplan.data.MensaDay;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

/**
 * Created by bschramke on 21.04.15.
 */
@EViewGroup(R.layout.mensa_item)
public class MensaItemView extends RelativeLayout {

    @ViewById(R.id.textView_mensaDate)
    protected TextView mensaDate;

    @ViewsById({R.id.textView_mensaMeal0, R.id.textView_mensaMeal1, R.id.textView_mensaMeal2,
            R.id.textView_mensaMeal3, R.id.textView_mensaMeal4})
    protected List<TextView> mensaMeals;

    public MensaItemView(@NonNull Context context) {
        super(context);
    }

    public MensaItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(@NonNull MensaDay data){

        final int mensaMealsCount = mensaMeals.size();
        final List<String> meals = data.getMeals();
        final int mealsCount = meals.size();

        mensaDate.setText(data.getDate());

        for(int i = 0; i < mensaMealsCount; i++){
            final String meal = (i >= mealsCount)?"":meals.get(i);

            mensaMeals.get(i).setText(Html.fromHtml(meal));
        }
    }
}
