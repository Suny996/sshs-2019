package com.sshs.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sshs.core.customise.mapper.CommonMapper;
import com.sshs.core.message.Message;
import com.sshs.core.util.SystemUtil;
import com.sshs.security.error.SecurityErrorCode;
import com.sshs.security.password.PasswordEncoderFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Suny
 * @version 1.0.0
 */
@RestController
@RequestMapping
public class LoginController {
    @Resource
    CommonMapper commonMapper;

    @Value("${server.servlet.contextPath:}")
    String contextPath;
    /**
     * 密码
     */
    @Value("${core.defaultPassword:{bcrypt}$2a$10$Adv2Bz4PzCL5.kYUK6Wx8.YmN4BGIShRhD/T18Xhcmeidr9D3NqjC}")
    private String defaultPassword;

    @GetMapping("/menus/{parentId}")
    public Message getMenusLoginUser(@PathVariable("parentId") String parentId, @RequestParam(value = "locale", required = false) String locale) {
        if (StringUtils.isEmpty(locale)) {
            locale = SystemUtil.getLocale();
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Message.success(initMenu(user.getUsername(), parentId, locale));
    }

    /**
     * 重写的内置登录页面
     *
     * @param request
     * @param response
     */
    @GetMapping("/login")
    public void login(HttpServletRequest request,
                      HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            PrintWriter out = response.getWriter();
            out.write(createPage());
            out.flush();
            out.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写的内置登录页面
     *
     * @param params
     */
    @PutMapping("/change-password")
    public Message changePassword(@RequestBody Map<String, Object> params) {
        String userName = (String) params.get("username");
        String password = (String) params.get("password");
        String newPassword = (String) params.get("newpassword");
        String encodedPassword = commonMapper.getPasswordByUserName(userName);
        /**
         * 验证原密码
         */
        if (!PasswordEncoderFactory.matches(password, encodedPassword)) {
            return Message.failure(SecurityErrorCode.OLD_PASSWORD_IS_WRONG);
        }
        String newEncodedPassword = PasswordEncoderFactory.encode(newPassword);
        commonMapper.setNewPassword(userName, newEncodedPassword, SystemUtil.getCurrentUser().getUserCode(), new Date());
        return Message.success();
    }

    /**
     * 重写的内置登录页面
     *
     * @param params
     */
    @PutMapping("/reset-password")
    public Message resetPassword(@RequestBody Map<String, Object> params) {
        String userName = (String) params.get("username");
        String password = (String) params.get("password");
        String encodePassword = commonMapper.getPasswordByUserName(SystemUtil.getCurrentUser().getUserCode());
        //验证重置密码的管理员密码
        if (!PasswordEncoderFactory.matches(password, encodePassword)) {
            return Message.failure(SecurityErrorCode.PASSWORD_IS_WRONG);
        }
        commonMapper.setNewPassword(userName, defaultPassword, SystemUtil.getCurrentUser().getUserCode(), new Date());
        return Message.success();
    }

    /**
     * 初始化菜单
     *
     * @param userName
     * @param parentId
     * @return
     */
    List<Map<String, Object>> initMenu(String userName, String parentId, String locale) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        if (StringUtils.isNotEmpty(locale)) {
            locale = locale.replace("-", "_");
        }
        String language = locale.split("_")[0];
        String country = locale.split("_")[1];
        params.put("parentId", parentId);
        params.put("userCode", userName);
        params.put("language", language);
        params.put("country", country);
        List<Map<String, Object>> userMenus = commonMapper.findUserMenus(params);
        List<Map<String, Object>> menus = new ArrayList<>();
        for (Map<String, Object> menu : userMenus) {
            //menu = BusiUtil.tranMapKey(menu,"1");
            menus.add(menu);
        }
        for (Map<String, Object> menu : menus) {
            menu.put("children", initMenu(userName, (String) menu.get("code"), locale));
        }
        return menus;
    }


    private String createPage() {
        String page = "<!DOCTYPE html><html lang='en'><head>"
                + "    <meta charset='utf-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
                + "    <meta name='description' content=''>"
                + "    <meta name='author' content=''>"
                + "    <title>Please sign in</title>"
                + "    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M' crossorigin='anonymous'>"
                + "    <link href='https://getbootstrap.com/docs/4.0/examples/signin/signin.css' rel='stylesheet' crossorigin='anonymous'/>"
                + "  </head>"
                + "<body>"
                + "<div class='container'>"
                + formLogin()
                // + oauth2LoginLinks(contextPath, this.oauth2AuthenticationUrlToClientName)
                + "    </div>"
                + "  </body>"
                + "</html>";

        return page;
    }

    /**
     * @return
     */
    private String formLogin() {
        return "      <form class='form-signin' method='post' action='" + contextPath + "/login'>"
                + "        <h2 class='form-signin-heading'>Please sign in</h2>"
                + "        <p>"
                + "          <label for='username' class='sr-only'>Username</label>"
                + "          <input type='text' id='username' name='username' class='form-control' placeholder='Username' required autofocus>"
                + "<label for='password' class='sr-only'>Password</label>"
                + "<input type='password' id='password' name='password' class='form-control' placeholder='Password' required>"
                + "        </p>"
                + "        <button class='btn btn-lg btn-primary btn-block' type='submit'>Sign in</button>"
                + "      </form>";
    }

    private static String csrfToken(CsrfToken token) {
        return "          <input type='hidden' name='" + token.getParameterName() + "' value='" + token.getToken() + "'>";
    }
}