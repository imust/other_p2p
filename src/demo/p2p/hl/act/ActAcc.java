package demo.p2p.hl.act;

import org.androidannotations.annotations.EActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import demo.p2p.hl.R;
import demo.p2p.hl.base.BaseActivity;

@EActivity(R.layout.act_acc)
public class ActAcc extends BaseActivity {
    
    public static void start(Context context) {
        context.startActivity(new Intent(context, ActAcc_.class));
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
}
