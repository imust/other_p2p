package demo.p2p.hl.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

    public static String getString(String json, String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return null;
        }
        
    }
    
    public static <T> List<T> getObjectList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<T>>(){}.getType();
        return new Gson().fromJson(json, type);
    }
    
    public static <T> T getObject(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
    
    
}
