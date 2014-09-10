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
import demo.p2p.hl.data.Bank;

@EViewGroup(R.layout.list_item_simple_string)
public class ItemViewBank extends LinearLayout implements AdapterView<Bank>{

    @ViewById
    TextView mContent;
    
    private Bank mData;
    
    public ItemViewBank(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewBank(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewBank(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(Bank data) {
        mData = data;
        bindContent();
    }
    
    public void bindContent() {
        mContent.setText(mData.name);
    }
    
    @Click(R.id.mContent)
    public void onContentClick() {
        EventBus.getDefault().postSticky(mData);
    }
    
    
    
    
    
    
    

}
