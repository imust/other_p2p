package demo.p2p.hl.data;

import java.io.Serializable;
import java.util.ArrayList;

import demo.p2p.hl.base.AdapterData;

public class Bank implements AdapterData, Serializable {
    
    public static final String[] BANKS = {"中国工商银行","招商银行",
        "中国建设银行","中国农业银行","中国银行","浦发银行","深圳发展银行",
        "兴业银行","北京银行","中国光大银行","交通银行","中国民生银行",
        "中信银行","广发银行","平安银行","邮政储蓄银行","江苏银行",
        "上海农商银行","上海银行","华夏银行"};
    
    public String name;
    
    public Bank(String name) {
        this.name = name;
    }
    
    public static ArrayList<Bank> createDefault() {
        ArrayList<Bank> banks = new ArrayList<Bank>();
        for (int i=0;i<BANKS.length;i++) {
            banks.add(new Bank(BANKS[i]));
        }
        return banks;
    }
    
}
