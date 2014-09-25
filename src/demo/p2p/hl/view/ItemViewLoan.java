package demo.p2p.hl.view;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.greenrobot.event.EventBus;
import demo.p2p.hl.R;
import demo.p2p.hl.base.AdapterView;
import demo.p2p.hl.data.Loan;

@EViewGroup(R.layout.list_item_loan)
public class ItemViewLoan extends LinearLayout implements AdapterView<Loan>{

    
    @ViewById
    TextView mTitle;
    
    @ViewById
    TextView mProgressText;
    
    @ViewById
    TextView mAmount;
    
    @ViewById
    TextView mRow;
    
    @ViewById
    TextView mTerm;
    
    @ViewById
    Button mLoan;
    
    @ViewById
    ProgressBar mProgressBar;

    private Loan mData;
    
    public ItemViewLoan(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ItemViewLoan(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemViewLoan(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void bindData(Loan data) {
        mData = data;
        bindTitle();
        bindProgress();
        bindAmount();
        bindRow();
        bindTerm();
    }
    
    public void bindTitle() {
        mTitle.setText(mData.title);
    }
    
    public void bindProgress() {
        mProgressBar.setProgress(mData.progress);
        mProgressText.setText(mData.progress + "%");
        if (mData.progress == 100) {
            mLoan.setText("已结束");
            mLoan.setEnabled(false);
        } else {
            mLoan.setText("投　标");
            mLoan.setEnabled(true);
        }
                
    }
    
    public void bindAmount() {
        mAmount.setText("¥" + mData.amount);
    }
    
    public void bindRow() {
        mRow.setText("年利率:" + mData.row + "%");
    }
    
    public void bindTerm() {
        mTerm.setText("借款期限:" + mData.term + "个月");
    }
    
    @Click(R.id.mLoan)
    public void onLoanClick() {
        EventBus.getDefault().post(mData);
    }
    
    
    
    
    
    
    
    
    

}
