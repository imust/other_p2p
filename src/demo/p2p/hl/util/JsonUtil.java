package demo.p2p.hl.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    public static String getString(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return null;
        }
        
    }
    
}
