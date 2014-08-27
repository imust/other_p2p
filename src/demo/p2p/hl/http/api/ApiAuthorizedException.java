package demo.p2p.hl.http.api;

public class ApiAuthorizedException extends ApiException {

    private static final long serialVersionUID = 1L;

    public ApiAuthorizedException() {
        super("登录状态异常");
    }

    
}
