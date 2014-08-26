package demo.p2p.hl.app;

import demo.p2p.hl.data.User;

public class UserSession {
    
    public static UserSession mInstance;
    
    private User mUser;
    
    private UserSession(User user) { 
        mUser = user;
    }
    
    public synchronized static UserSession open(User user) {
        if (mInstance == null) {
            mInstance = new UserSession(user);
        }
        return mInstance;
    }
    
    public User getUser() {
        return mUser;
    }
    
    public boolean isLogin() {
        return mUser != null;
    }
    
}
