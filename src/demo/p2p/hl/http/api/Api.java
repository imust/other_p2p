package demo.p2p.hl.http.api;

import com.google.gson.GsonBuilder;

import demo.p2p.hl.config.Config;
import demo.p2p.hl.data.User;
import demo.p2p.hl.data.UserResult;
import demo.p2p.hl.http.HttpHelper;
import demo.p2p.hl.util.Lg;

public class Api {
    
    
    public static User login(String username, String password) throws ApiException {
        String result = 
        HttpHelper.getDefault().get(Config.createUri("user", "logins"), true, 
                "username", username , "pwd", password);
        User user = new GsonBuilder().create().fromJson(result, UserResult.class).bean;
        return user;
    }
}
