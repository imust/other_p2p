package demo.p2p.hl.http.api;

import java.util.List;

import demo.p2p.hl.data.BankCard;
import demo.p2p.hl.data.BankCardListResult;
import demo.p2p.hl.data.Bid;
import demo.p2p.hl.data.BidListResult;
import demo.p2p.hl.data.Loan;
import demo.p2p.hl.data.LoanListResult;
import demo.p2p.hl.data.Message;
import demo.p2p.hl.data.MessageListResult;
import demo.p2p.hl.data.RechargeResult;
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
                "username", username , 
                "pwd", password,
                "velify", ""
                );
        return JsonUtil.getObject(result, UserResult.class).bean;
    }
    
    /**
     * 用户注册
     * @param realName
     * @param password
     * @param id
     * @param phone
     * @param code
     * @throws ApiException
     */
    public static void reg(String realName, String password, String id, String phone, String code) throws ApiException {
        HttpHelper.getDefault().put(createUri("user", ""),  
                "phoneCode", code , 
                "pwd", password, 
                "idCard", id, 
                "phone", phone, 
                "realName", realName);
    }
    
    /**
     * 充值
     * @return
     * @throws ApiException
     */
    public static String recharge() throws ApiException {
        String result =
        HttpHelper.getDefault().post(createUri("pay", "recharge_ajax"), 
                "amount", 100);
        return JsonUtil.getObject(result, RechargeResult.class).bean.url;
    }
    
    /**
     * 向当前绑定的手机发送验证码
     * @throws ApiException
     */
    public static void sendPhoneCode(String phone, boolean voice) throws ApiException {
        HttpHelper.getDefault().get(createUri("system", "phoneCode"), true, 
                "phone", phone,
                "voice", voice);
    }
    
    /**
     * 向宝付要验证码
     * @throws ApiException
     */
    public static void sendPayCode() throws ApiException {
        HttpHelper.getDefault().get(createUri("user", "payCode"));
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
     * 投标记录/ 最近待还
     * @param near 是否只显示最近待还
     * @return
     * @throws ApiException
     */
    public static List<Bid> getBidList(boolean near) throws ApiException {
        String result = 
        HttpHelper.getDefault().get(createUri("user", "getUserBids"), true, "page", 1, "near", near);
        return JsonUtil.getObject(result, BidListResult.class).list;
    }
    
    /**
     * 查询银行卡列表
     * @return
     * @throws ApiException
     */
    public static List<BankCard> getBankCardList() throws ApiException {
        String result = 
        HttpHelper.getDefault().get(createUri("user", "bank"));
        return JsonUtil.getObject(result, BankCardListResult.class).list;
    }
    
    /**
     * 获取所有消息
     * @return
     * @throws ApiException
     */
    public static List<Message> getMessageList(boolean unreadOnly) throws ApiException {
        String result = 
        HttpHelper.getDefault().get(createUri("user", "message"), true, "page", 1, "unread", unreadOnly);
        return JsonUtil.getObject(result, MessageListResult.class).list;
    }
    
    /**
     * 更新消息设置
     * @throws ApiException
     * @param humanity 是否开启免打扰
     * @param messageSetting 开启了的消息类型,  sms|weixin|email
     */
    public static void updateMsgSetting(boolean humanity, String messageSetting) throws ApiException {
        HttpHelper.getDefault().post(createUri("user", "messageSetting"), 
                "page", 1, "humanity", humanity, "messageSetting", messageSetting);
    }
    
    /**
     * 标记消息已读
     * @return
     * @throws ApiException
     */
    public static void updateMessageStatus() throws ApiException {
        HttpHelper.getDefault().post(createUri("user", "message", "-1"),"all", true);
    }
    
    /**
     * 添加银行卡
     * @param bankCard
     * @throws ApiException
     */
    public static void createBackCard(BankCard bankCard, String code) throws ApiException {
        HttpHelper.getDefault().put(createUri("user", "bank"), 
                "card", bankCard.card,
                "bankNmae", bankCard.bankName,
                "province", bankCard.province, 
                "city", bankCard.city, 
                "add", bankCard.add, 
                "phoneCode", code
                );
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
