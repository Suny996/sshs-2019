package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.message.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Suny
 * @date 2019-1-24
 */
public class SshsSessionExpiredStrategy implements SessionInformationExpiredStrategy {
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse().setContentType("application/json;charset=utf-8");
        event.getResponse().setStatus(603);
        Message message = Message.failure("100603");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = event.getResponse().getWriter();
        out.write(om.writeValueAsString(message));
        out.flush();
        out.close();
        return;
    }
}
