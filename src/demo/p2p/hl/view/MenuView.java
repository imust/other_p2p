package demo.p2p.hl.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import demo.p2p.hl.R;

@EViewGroup(R.layout.menu)
public class MenuView extends LinearLayout {

    public MenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context) {
        super(context);
    }

    @AfterViews
    void init() {
        this.setOrientation(VERTICAL);
        this.setBackgroundResource(R.drawable.bg_menu);
    }

    @Click
    void home() {
        
    }
    
    @Click
    void trade() {
        
    }
    
    @Click
    void person() {
        
    }
    
    @Click
    void setting() {
        
    }
    
    @Click
    void change() {
        
    }
    
    @Click
    void exit() {
        
    }
    
}
