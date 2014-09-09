package demo.p2p.hl.view;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.base.AdapterView;
import demo.p2p.hl.data.Message;

@EViewGroup(R.layout.list_item_simple_string)
public class ItemViewMsg extends LinearLayout implements AdapterView<Message>{

    @ViewById
    TextView mContent;
    
    private Message mData;
    
    public ItemViewMsg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewMsg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewMsg(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(Message data) {
        mData = data;
        bindContent();
    }
    
    public void bindContent() {
        mContent.setText(mData.content);
        
        mContent.setBackgroundResource(mData.isRead ? 
                R.drawable.bg_item_single_pressed :
                    R.drawable.bg_item_single);
        
        mContent.setTextColor(mData.isRead ?
                getResources().getColor(R.color.text_grey) :
                    getResources().getColor(R.color.text_black)
                );
        
        mContent.setPadding(
                getResources().getDimensionPixelOffset(R.dimen.p10),
                getResources().getDimensionPixelOffset(R.dimen.p10),
                getResources().getDimensionPixelOffset(R.dimen.p10),
                getResources().getDimensionPixelOffset(R.dimen.p10));
    }
    
    
    
    
    
    
    
    

}
