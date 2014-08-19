package demo.p2p.hl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.GsonBuilder;

import demo.p2p.hl.data.UserResult;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.HttpHelper;

//17092848584
//890218
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    
	@ViewById
	ListView mList;
	
	@AfterViews
	void test () {
	    testHttp();
	}
	
	@Background
	void testHttp() {
//	    String result = 
//	    HttpHelper.get("https://www.ddw0817.com/user/login", true, "username", "17092848584" , "pwd", "890218")
//	        .body();
//	    Log.d("test", result);
//	    
//	    User user = new GsonBuilder().create().fromJson(result, UserResult.class).bean;
//	    Log.d("test", user.toString());
	    
	}
	
	
}
