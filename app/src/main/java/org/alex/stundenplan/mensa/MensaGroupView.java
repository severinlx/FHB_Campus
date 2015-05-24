package org.alex.stundenplan.mensa;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.alex.stundenplan.R;
import org.alex.stundenplan.data.MensaDay;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;
import org.androidannotations.annotations.res.DimensionRes;

import java.util.List;

/**
 * Created by bschramke on 06.05.15.
 */
@EView
public class MensaGroupView extends CheckedTextView {

    @DimensionPixelSizeRes(R.dimen.mensa_group_margin_left)
    int marginLeft;

    @DimensionPixelSizeRes(R.dimen.mensa_group_padding_left)
    int paddingLeft;

    @DimensionPixelSizeRes(R.dimen.mensa_group_padding_top)
    int paddingTop;

    @DimensionPixelSizeRes(R.dimen.listPreferredItemHeight)
    int layoutHeight;

    public MensaGroupView(@NonNull Context context) {
        this(context, null);
    }

    public MensaGroupView(@NonNull Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.checkedTextViewStyle);
    }

    public MensaGroupView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterInject
    void setupView(){
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            this.setTextAlignment(TEXT_ALIGNMENT_TEXT_END);
        }

        //i commented out this lines because they cause a bug on android 4.4 and app crash

       //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,layoutHeight);
       // params.setMargins(marginLeft,0,0,0);
      //  this.setLayoutParams(params);

        this.setTextAppearance(getContext(), R.style.FHB_TextAppearance_Mensa_Group);
        this.setPadding(paddingLeft,paddingTop,0,0);
    }
}
