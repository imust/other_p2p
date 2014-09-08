package demo.p2p.hl.data;

import demo.p2p.hl.base.AdapterData;
import demo.p2p.hl.util.DateUtil;

public class Bid implements AdapterData {
    
    /**
     * 已提交
     */
    public static final int STATUS_COMMITED = 0;
    /**
     * 成功
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 失败
     */
    public static final int STATUS_FAIL = 2;
    
    /**
     * 标的标题
     */
    public String title;
    /**
     * 投标金额
     */
    public float amount;
    /**
     * 状态
     */
    public int status;
    /**
     * 状态描述
     */
    public String statusStr;
    /**
     * 还款日期, 可能为空
     */
    public long returnDate;
    /**
     * 创建日期
     */
    public long createDate;
    /**
     * id
     */
    public int id;
    
    public String getReturnDate() {
        return returnDate == 0 ? "无" : DateUtil.format(returnDate);
    }
    
    public String getCreateDate() {
        return DateUtil.format(createDate);
    }
    
    
}
