package demo.p2p.hl.app;

import android.content.Context;
import demo.p2p.hl.data.User;
import demo.p2p.hl.util.Sp;

public class UserSession {
    
    public static UserSession mInstance;
    
    private User mUser;
    
    private Context mContext;
    
    private Sp mSp;
    
    private UserSession() { 
    }
    
    public synchronized static UserSession get() {
        if (mInstance == null) {
            mInstance = new UserSession();
        }
        return mInstance;
    }
    
    public void init(Application application) {
        mContext = application;
    	mSp = new Sp(mContext);
    }
    
    public String getSession() {
        return mSp.getString(Sp.SP_USER_SESSION, null);
    }
    
    public void setSession (String session) {
        mSp.putString(Sp.SP_USER_SESSION, session);
    }
    
    public void clearSession() {
        setSession(null);
    }
    
    public boolean hasSession() {
        return getSession() != null;
    }
    
    public User getUser() {
        return mUser;
    }
    
    public void setUser(User user) {
        mUser = user;
    }
    
    public boolean isLogin() {
        return mUser != null;
    }

    public void clear() {
        mUser = null;
    	clearSession();
    }
    
}
