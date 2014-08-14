package demo.p2p.hl.config;

public class Config {
    
    public static final String BASE_URI = "https://www.ddw0817.com";

    
    /**
     * 拼接URI , 传的参数会用/分隔
     * @param str
     * @return
     */
    public static String createUri(String... str) {
        StringBuffer sb = new StringBuffer(BASE_URI);
        if (str != null && str.length > 0) {
            for (String s : str) {
                sb.append("/");
                sb.append(s);
            }
        }
        return sb.toString();
    }
}
