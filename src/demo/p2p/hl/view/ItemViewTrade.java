package demo.p2p.hl.view;

import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

@EViewGroup
public class ItemViewTrade extends LinearLayout {

    public ItemViewTrade(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewTrade(Context context, AttributeSet attrs) {
        super(context, attrs);
    } 

    public ItemViewTrade(Context context) {
        super(context);
    }

}
