package demo.p2p.hl.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.AdapterView;
import demo.p2p.hl.data.Bid;

@EViewGroup(R.layout.list_item_bid)
public class ItemViewBid extends LinearLayout implements AdapterView<Bid>{

    @ViewById
    TextView mTitle;
    @ViewById
    TextView mCreateDate;
    @ViewById
    TextView mReturnDate;
    @ViewById
    Button mStatus;
    @ViewById
    TextView mAmount;

    private Bid mData;
    
    public ItemViewBid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewBid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewBid(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(Bid data) {
        mData = data;
        bindTitle();
        bindCreateDate();
        bindAmount();
        bindRetrunDate();
        bindStatus();
    }
    
    public void bindTitle() {
        mTitle.setText(mData.title);
    }
    
    public void bindCreateDate() {
        mCreateDate.setText("标的时间:" + mData.getCreateDate());
    }
    
    public void bindAmount() {
        mAmount.setText("¥" + mData.amount);
    }
    
    public void bindRetrunDate() {
        mReturnDate.setText("还款时间:" + mData.getReturnDate());
    }
    
    public void bindStatus() {
        mStatus.setText(mData.statusStr);
    }
    
    

}
