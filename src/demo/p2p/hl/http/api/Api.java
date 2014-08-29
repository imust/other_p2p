package demo.p2p.hl.http.api;

import com.google.gson.GsonBuilder;

import demo.p2p.hl.data.User;
import demo.p2p.hl.data.UserResult;
import demo.p2p.hl.http.HttpHelper;

public class Api {
    
//    public static final String URI_ROOT = "https://www.ddw0817.com";
    public static final String URI_ROOT = "https://beta.ddw0817.com";
    
    
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws ApiException
     */
    public static User login(String username, String password) throws ApiException {
        String result = 
        HttpHelper.getDefault().get(createUri("user", "logins"), true, 
                "username", username , "pwd", password);
        User user = new GsonBuilder().create().fromJson(result, UserResult.class).bean;
        return user;
    }
    
    public static void getLoanList() throws ApiException {
        String result = 
                HttpHelper.getDefault().get(createUri("loan"), true, "page", 1);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static String createUri(String... str) {
        StringBuffer sb = new StringBuffer(URI_ROOT);
        if (str != null && str.length > 0) {
            for (String s : str) {
                sb.append("/");
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
}
