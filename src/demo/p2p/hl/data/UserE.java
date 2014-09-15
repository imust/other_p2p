package demo.p2p.hl.data;

/**
 * 用户收益 
 * @date 2014-9-15
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class UserE {
    public float yesterday;
    public float total;
    
    public String getYesterdayString() {
        return yesterday == -1 ? "敬请期待" : String.valueOf(yesterday);
    }
    
    public String getTotalString() {
        return total == -1 ? "敬请期待" : String.valueOf(total);
    }
}
