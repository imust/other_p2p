package demo.p2p.hl.http.api;

import java.util.List;

import demo.p2p.hl.data.Loan;
import demo.p2p.hl.data.LoanListResult;
import demo.p2p.hl.data.User;
import demo.p2p.hl.data.UserResult;
import demo.p2p.hl.http.HttpHelper;
import demo.p2p.hl.util.JsonUtil;

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
        return JsonUtil.getObject(result, UserResult.class).bean;
    }
    
    
    /**
     * 获取当前登录用户
     * @return
     * @throws ApiException
     */
    public static User getUser() throws ApiException {
    	String result = 
    	HttpHelper.getDefault().get(createUri("user", "getUser"));
    	return JsonUtil.getObject(result, UserResult.class).bean;
    }
    
    /**
     * 交易中心
     * @return
     * @throws ApiException
     */
    public static List<Loan> getLoanList() throws ApiException {
        String result = 
        HttpHelper.getDefault().get(createUri("loan"), true, "page", 1);
        return JsonUtil.getObject(result, LoanListResult.class).list;
    }
    
    
    /**
     * 更新消息设置
     * @throws ApiException
     * @param humanity 是否开启免打扰
     * @param messageSetting 开启了的消息类型,  sms|weixin|email
     */
    public static void updateMsgSetting(boolean humanity, String messageSetting) throws ApiException {
        HttpHelper.getDefault().post(createUri("user", "messageSetting"), true, 
                "page", 1, "humanity", humanity, "messageSetting", messageSetting);
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
