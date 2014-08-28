package demo.p2p.hl.data;

public class Loan {
    
    /**
     * 借款金额
     */
    public float amount;
    
    /**
     * 担保者
     */
    public String assure;
    
    /**
     * 创建时间
     */
    public String createDate;
    
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
