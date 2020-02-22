package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.message.Message;
import com.sshs.security.error.SecurityErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Suny
 * @date 2019-1-23
 */
public final class SshsInvalidSessionStrategy implements InvalidSessionStrategy {
    private final Log logger = LogFactory.getLog(getClass());
    //private final String destinationUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private boolean createNewSession = true;

  /*  public ScaiInvalidSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl),
                "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }*/

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(602);
        Message message = Message.failure(SecurityErrorCode.AUTHORISE_INVALID);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(message));
        out.flush();
        out.close();
        return;
    }

    /**
     * Determines whether a new session should be created before redirecting (to avoid
     * possible looping issues where the same session ID is sent with the redirected
     * request). Alternatively, ensure that the configured URL does not pass through the
     * {@code SessionManagementFilter}.
     *
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
