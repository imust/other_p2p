package demo.p2p.hl.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.app.UserSession;
import demo.p2p.hl.data.Loan;
import demo.p2p.hl.http.api.Api;
import demo.p2p.hl.http.api.ApiException;
import demo.p2p.hl.util.ToastUtil;

@EViewGroup(R.layout.view_loan_action)
public class ViewLoanAction extends LinearLayout {

    @ViewById
    TextView mMaxAmount;
    
    @ViewById
    TextView mBalance;

    @ViewById
    EditText mAmount;
    
    @ViewById
    View mTopContainer;
    
    @ViewById
    View mBottomContainer;
    
    @AnimationRes(R.anim.push_down_out)
    Animation mPushDownOut;
    
    @AnimationRes(R.anim.push_down_in)
    Animation mPushDownIn;

    @AnimationRes(R.anim.push_up_out)
    Animation mPushUpOut;
    
    @AnimationRes(R.anim.push_up_in)
    Animation mPushUpIn;
    
    private Loan mLoan;
    
    private CommonDialog mCurrentDialog;
    
    public ViewLoanAction(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ViewLoanAction(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewLoanAction(Context context) {
        super(context);
    }

    @AfterViews
    void init() {
        this.setOrientation(VERTICAL);
        mBalance.setText(String.valueOf(UserSession.get().getUser().balance));
        mPushUpOut.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }
            public void onAnimationRepeat(Animation animation) {
            }
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }
        });
    }
    
    public void show(Loan loan) {
        mLoan = loan;
        float maxAmout = loan.amount * (1 - loan.progress * 1.0f / 100);
        mMaxAmount.setText(String.valueOf(maxAmout));
        mAmount.setText(String.valueOf((int)Math.min(maxAmout, UserSession.get().getUser().balance)));
        this.setVisibility(VISIBLE);
        mTopContainer.startAnimation(mPushDownIn);
        mBottomContainer.startAnimation(mPushUpIn);
    }
    
    public void hide() {
        mTopContainer.startAnimation(mPushUpOut);
        mBottomContainer.startAnimation(mPushDownOut);
    }
    
    @Click(R.id.mCancelButton)
    void onCancelClick() {
        hide();
    }
    
    @Click(R.id.mConfirmButton)
    void onConfirmClick() {
        String amout = mAmount.getText().toString();
        
        if (TextUtils.isEmpty(amout)) {
            ToastUtil.getDefault().show("请输入投标金额");
            return;
        }
        
        showCommitDialog();
        commit(Integer.parseInt(amout));
    }
    
    @Background
    void commit(int amount) {
        try {
            Api.loan(mLoan.id, amount);
            onCommitSuccess();
        } catch (ApiException e) {
            onCommitFail(e);
        }
    }
    
    @UiThread
    void onCommitSuccess() {
        cancelDialog();
        ToastUtil.getDefault().show("操作成功");
        hide();
    }
    
    @UiThread
    void onCommitFail(ApiException e) {
        ToastUtil.getDefault().show(e.getErrorMessage());
        cancelDialog();
    }
    
    void showCommitDialog() {
        mCurrentDialog = new CommonDialog(getContext())
            .setMessage("投标中...");
        mCurrentDialog.show();
    }
    
    void cancelDialog() {
        if (mCurrentDialog != null) {
            mCurrentDialog.dismiss();
            mCurrentDialog = null;
        }
    }
    
}
