package demo.p2p.hl.view;

import android.app.Dialog;
import android.content.Context;
import demo.p2p.hl.R;
import demo.p2p.hl.view.CommonDialogView.OnCancelClickListener;
import demo.p2p.hl.view.CommonDialogView.OnConfirmClickListener;

/**
 * 简单的提示对话框, 包括, 一个确定按钮 一个取消按钮 一个标题 一段提示消息 一个关闭按钮</br>
 * Dialog暂时还不能用annotation, 就直接写吧...
 * @date 2014-8-27
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class CommonDialog extends Dialog {
    
    private CommonDialogView mDialogView;
    
    public CommonDialog(Context context) {
        super(context, R.style.common_dialog);
        mDialogView = CommonDialogView_.build(context);
        setContentView(mDialogView);
        setCancelable(false);
        setDefaultEvent();
    }
    
    private void setDefaultEvent() {
        mDialogView.setOnCancelClickListener(new OnCancelClickListener() {
            public void OnCancel() {
                CommonDialog.this.dismiss();
            }
        });
    }
    
    public CommonDialog setOnCancelClickListener(OnCancelClickListener mOnCancelClickListener) {
        mDialogView.setOnCancelClickListener(mOnCancelClickListener);
        return this;
    }

    public CommonDialog setOnConfirmClickListener(OnConfirmClickListener mOnConfirmClickListener) {
        mDialogView.setOnConfirmClickListener(mOnConfirmClickListener);
        return this;
    }

    public CommonDialog setTitle(String text) {
        mDialogView.setTitle(text);
        return this;
    }
    
    public CommonDialog setMessage(String text) {
        mDialogView.setMessage(text);
        return this;
    }
    
    public CommonDialog showConfirm() {
        mDialogView.showConfirm();
        return this;
    }
    
    public CommonDialog showCancel() {
        mDialogView.showCancel();
        return this;
    }

}
