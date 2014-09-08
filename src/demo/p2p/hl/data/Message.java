package demo.p2p.hl.data;

import demo.p2p.hl.base.AdapterData;

public class Message implements AdapterData {
    /**
     * 消息内容
     */
    public String content;
    /**
     * 是否已读
     */
    public boolean isRead;
}
