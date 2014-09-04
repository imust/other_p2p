package demo.p2p.hl.data;

import demo.p2p.hl.base.AdapterData;

public class Loan implements AdapterData {
    
    //3=投标中,4=还款中,5=已流标，6已还款，7已逾期，8逾期已赔付,9逾期已偿还，10逾期已赔付偿还
    // 目前只处理  3 和 !3 就行了..... 
    public static final int STATUS_LOAN_ING = 3;

            
    /**
     * 借款金额
     */
    public float amount;
    
    /**
     * 担保者(可为空)
     */
    public String assure;
    
    /**
     * 创建时间
     */
    public long createDate;
    
    /**
     * id
     */
    public int id;
    
    /**
     * 投标进度
     */
    public int progress;
    
    /**
     * 利率
     */
    public int row;
    
    /**
     * 状态
     */
    public int status;
    
    /**
     * 状态说明
     */
    public String statusStr;
    
    /**
     * 借款期限
     */
    public int term;
    
    /**
     * 标的标题
     */
    public String title;

    @Override
    public String toString() {
        return "Loan [amount=" + amount + ", assure=" + assure
                + ", createDate=" + createDate + ", id=" + id + ", progress="
                + progress + ", row=" + row + ", status=" + status
                + ", statusStr=" + statusStr + ", term=" + term + ", title="
                + title + "]";
    }
    
    
    
}
