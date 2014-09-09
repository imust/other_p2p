package demo.p2p.hl.data;

import com.google.gson.annotations.SerializedName;

import demo.p2p.hl.base.AdapterData;

public class BankCard implements AdapterData {
    
    /**
     * 卡号后四位
     */
    public String card;
    
    /**
     * 银行
     */
    public String bankName;
    
    /**
     * 省份
     */
    public String province;
    
    /**
     * 城市
     */
    public String city;
    
    /**
     * 开户行........尼玛这什么名字
     */
    public String add;
    
    /**
     * 是否默认提现银行卡 
     */
    @SerializedName("default")
    public boolean isDefault;
    
    /**
     * user id 
     */
    public int uid;
    
    /**
     * 验证码
     */
    public String phoneCode;
    
    /**
     * id 
     */
    public int id;
    
}
