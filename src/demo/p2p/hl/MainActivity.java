package demo.p2p.hl;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.ListView;
import demo.p2p.hl.base.CustomerAdapter;
import demo.p2p.hl.data.TestData;
import demo.p2p.hl.view.TestView;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	ListView mList;
	
	@AfterViews
	void test() {
		ArrayList<TestData> list = new ArrayList<TestData>();
		TestData data = new TestData();
		data.name = "name1";
		data.address = "address1";
		list.add(data);
		data = new TestData();
		data.name = "name2";
		data.address = "address2";
		list.add(data);
		
		CustomerAdapter<TestData, TestView> adapter = 
				new CustomerAdapter<TestData, TestView>(this) {};
		adapter.setList(list);
		
		mList.setAdapter(adapter);
	}
	
}
