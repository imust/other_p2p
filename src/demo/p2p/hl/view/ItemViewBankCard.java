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
import demo.p2p.hl.data.BankCard;

@EViewGroup(R.layout.list_item_bank_card)
public class ItemViewBankCard extends LinearLayout implements AdapterView<BankCard>{

    @ViewById
    TextView mBankName;
    @ViewById
    TextView mCard;
    @ViewById
    Button mDefault;

    private BankCard mData;
    
    public ItemViewBankCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewBankCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewBankCard(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(BankCard data) {
        mData = data;
        bindBankName();
        bindCard();
        bindDefault();
    }
    
    public void bindBankName() {
        mBankName.setText(mData.bankName);
    }
    
    public void bindCard() {
        mCard.setText("************" + mData.card);
    }
    
    public void bindDefault() {
        mDefault.setText(mData.isDefault ? "默认" : "设为默认");
    }
    
    

}
