package demo.plug.core;

import java.io.IOException;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import cn.trinea.android.common.util.PackageUtils;
import demo.p2p.hl.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	TextView text;
	
	@Click
	public void button1(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.parse("file:///sdcard/demo.plug.client.apk"),  
                "application/vnd.android.package-archive");  
        startActivity(intent);  
	}
	
	@Click
	public void button2(View v) {
		int i = PackageUtils.installSilent(this, "/sdcard/demo.plug.client.apk");
		Log.i("test", i + "");
		if (i==1) {
			text.setText("success");
		} else {
			text.setText("fail:" + i);
		}
	}
	
	@Click
	public void button3(View v) {
		try {
			Runtime.getRuntime().exec("su");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Click
	public void button4(View v) {
		int i = PackageUtils.installSilent(this, "/sdcard/demo.plug.client.old.apk");
		Log.i("test", i + "");
		if (i==1) {
			text.setText("success");
		} else {
			text.setText("fail:" + i);
		}
	}
	
	@Click
	public void button5(View v) {
		Intent intent = new Intent(); 
		ComponentName componentName = new ComponentName("demo.plug.client","demo.plug.client.MainActivity");
		intent.setComponent(componentName); 
		startActivity(intent);
	}
	
	
}
