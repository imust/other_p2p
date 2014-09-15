package demo.p2p.hl.data;

import java.io.Serializable;

public class User implements Serializable {
    /** 已担保额度 */
    public float assure;
    /** 最大担保额度 */
    public float assureMax;
    /** 账户可用余额 */
    public float balance;
    /** 已借入金额 */
    public float borrowIn;
    /** 已借出金额 */
    public float borrowOut;
    /** 创建时间 */
    public String createDate;
    /** 邮箱前2位 */
    public String email;
    /** id */
    public int id;
    /** 冻结金额 */
    public float lockMoney;
    /** 手机号后四位 */
    public String phone;
    /** 姓名 */
    public String realName;
    /** 身份证后四位 */
    public String idCard;
    /** 额外信息 */
    public UserExt ext;
    /** 收益信息 */
    public UserE ue;
    /** 可能返回的连接到宝付注册页面的url地址 */
    public String url;
    /** 暂存的密码 */
    public String password;
    
    @Override
    public String toString() {
        return "User [assure=" + assure + ", assureMax=" + assureMax
                + ", balance=" + balance + ", borrowIn=" + borrowIn
                + ", borrowOut=" + borrowOut + ", createDate=" + createDate
                + ", email=" + email + ", id=" + id + ", lockMoney="
                + lockMoney + ", phone=" + phone + ", realName=" + realName
                + ", ext=" + ext + "]";
    }
    
    
}
