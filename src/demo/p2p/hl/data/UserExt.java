package demo.p2p.hl.data;

public class UserExt {
    /** 担保比例 */
    public int assureScale;
    /** 是否自动还款 */
    public boolean autoRepay;
    /** 头像地址 */
    public String headimg;
    /** 是否开启人性化设置 */
    public boolean humanity;
    /** 用户id */
    public int id;
    /** 消息设置 */
    public String messageSetting;
    /** 姓名 */
    public String name;
    /** 微信token */
    public String weixin;
    @Override
    public String toString() {
        return "UserExt [assureScale=" + assureScale + ", autoRepay="
                + autoRepay + ", headimg=" + headimg + ", humanity=" + humanity
                + ", id=" + id + ", messageSetting=" + messageSetting
                + ", name=" + name + ", weixin=" + weixin + "]";
    }
    
    
}
