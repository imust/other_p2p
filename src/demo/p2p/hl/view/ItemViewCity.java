package demo.p2p.hl.view;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.base.AdapterView;
import demo.p2p.hl.data.City;

@EViewGroup(R.layout.list_item_simple_string)
public class ItemViewCity extends LinearLayout implements AdapterView<City>{

    @ViewById
    TextView mContent;
    
    private City mData;
    
    public ItemViewCity(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewCity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewCity(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(City data) {
        mData = data;
        bindContent();
    }
    
    public void bindContent() {
        mContent.setText(mData.name);
    }
    
    @Click(R.id.mContent)
    public void onContentClick() {
        EventBus.getDefault().post(mData);
    }
    
    
    
    
    
    
    

}
