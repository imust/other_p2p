package demo.p2p.hl.view;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import demo.p2p.hl.R;

/**
 * 简单的提示对话框, 包括, 一个确定按钮 一个取消按钮 一个标题 一段提示消息 一个关闭按钮</br>
 * Dialog暂时还不能用annotation, 就直接写吧...
 * @date 2014-8-27
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
@EViewGroup(R.layout.view_common_dialog)
public class CommonDialogView extends LinearLayout {

    @ViewById
    TextView mTitle;
    
    @ViewById
    TextView mMessage;
    
    @ViewById
    View mConfirmButton;

    @ViewById
    View mCancelButton;
    
    private OnCancelClickListener mOnCancelClickListener;
    private OnConfirmClickListener mOnConfirmClickListener;
    
    public CommonDialogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CommonDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonDialogView(Context context) {
        super(context);
    }
    
    @Click(R.id.mConfirmButton)
    public void onConfirmClick() {
        if (mOnConfirmClickListener != null) {
            mOnConfirmClickListener.OnConfirm();
        }
    }
    
    @Click(R.id.mCancelButton)
    public void onCancelClick() {
        if (mOnCancelClickListener != null) {
            mOnCancelClickListener.OnCancel();
        }
    }
    
    public void setTitle(String text) {
        mTitle.setText(text);
        mTitle.setVisibility(View.VISIBLE);
    }
    
    public void setMessage(String text) {
        mMessage.setText(text);
        mMessage.setVisibility(View.VISIBLE);
    }
    
    public void showConfirm() {
        mConfirmButton.setVisibility(View.VISIBLE);
    }
    
    public void showCancel() {
        mCancelButton.setVisibility(View.VISIBLE);
    }
    
    public void setOnCancelClickListener(OnCancelClickListener mOnCancelClickListener) {
        this.mOnCancelClickListener = mOnCancelClickListener;
    }

    public void setOnConfirmClickListener(OnConfirmClickListener mOnConfirmClickListener) {
        this.mOnConfirmClickListener = mOnConfirmClickListener;
    }

    public interface OnConfirmClickListener {
        public void OnConfirm();
    }
    
    public interface OnCancelClickListener {
        public void OnCancel();
    }
    
    public interface OnCloseClickListener {
        public void OnClose();
    }
    
}
