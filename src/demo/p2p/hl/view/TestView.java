package demo.p2p.hl.view;

import android.content.Context;
import android.widget.TextView;
import demo.p2p.hl.R;
import demo.p2p.hl.data.TestData;

public class TestView extends AdapterView<TestData> {
	
	private TextView mName;
	private TextView mAddress;
	
	public TestView(Context context) {
		super(context);
	}

	@Override
	public void initView(Context context) {
		inflate(context, R.layout.test_view, this);
		mName = (TextView) findViewById(R.id.name);
		mAddress = (TextView) findViewById(R.id.address);
	}

	@Override
	public void bindData(TestData data) {
		mName.setText(data.name);
		mAddress.setText(data.address);
	}

	
}
