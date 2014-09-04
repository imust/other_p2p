package demo.p2p.hl.util;

import android.util.Log;

/**
 * 
 * 简单的Log工具, 仅用于Debug
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class Lg {
	
    public static boolean DEBUG = true;
    public static String DEFAULT_TAG = "TEST";
    
    
    public static void d() {
        d(DEFAULT_TAG, "HELLO WORLD");
    }
    
    public static void d(String log) {
        d(DEFAULT_TAG, log == null ? "null" : log);
    }
    
    public static void d(Object log) {
        d(log == null ? "null" : log.toString());
    }
   
    public static void d(String tag, String log) {
        if (DEBUG) {
            Log.d(tag, log);
        }
    }
	
    public static void e(String log) {
        e(DEFAULT_TAG, log == null ? "null" : log);
    }
    
    public static void e(Object log) {
        e(log == null ? "null" : log.toString());
    }
   
    public static void e(String tag, String log) {
        if (DEBUG) {
            Log.e(tag, log);
        }
    }
	
	
}
