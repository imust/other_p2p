package demo.p2p.hl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String format(long date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date(date));
    }
    
    public static String format(long date) {
        return format(date, "yyyy年MM月dd日 HH:mm:ss");
    }
    
    
    
}
