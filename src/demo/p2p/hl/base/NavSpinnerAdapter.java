package demo.p2p.hl.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import demo.p2p.hl.R;

public class NavSpinnerAdapter extends BaseAdapter {
    
    private String[] mData;
    private Context mContext;
    
    public static NavSpinnerAdapter create(Context context, int stringArrayRes) {
        return new NavSpinnerAdapter(context, stringArrayRes);
    }
    
    private NavSpinnerAdapter(Context context, int stringArrayRes) {
        mData = context.getResources().getStringArray(stringArrayRes);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.length;
    }

    @Override
    public Object getItem(int position) {
        return getCount() <=position ? null : mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_nav_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(mData[position]);
        textView.setTextColor(mContext.getResources().getColor(R.color.text_white));
        return convertView;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) getView(position, convertView, parent);
        textView.setTextColor(mContext.getResources().getColor(R.color.text_black));
        return textView;
    }
}
