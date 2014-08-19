package demo.p2p.hl.http.api;

import demo.p2p.hl.config.Config;
import demo.p2p.hl.data.User;
import demo.p2p.hl.http.HttpHelper;

public class Api {
    
    
    public static User login(String username, String password) {
        String result = 
        HttpHelper.get(Config.createUri("user", "login"), true, 
                "username", "17092848584" , "pwd", "890218");
        return null;
//        User user = new GsonBuilder().create().fromJson(result, UserResult.class).bean;
//        Log.d("test", user.toString());
        
    }
}
