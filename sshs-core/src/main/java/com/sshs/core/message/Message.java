package com.sshs.core.message;

import com.alibaba.fastjson.JSON;
import com.sshs.core.constant.Global;
import com.sshs.core.exception.BaseErrorCode;
import com.sshs.core.exception.CommonErrorCode;
import com.sshs.core.util.SystemUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Suny
 * @date 2018-09-23
 */
@ApiModel("用户信息")
public class Message<T> implements Serializable {
    private final static int SUCCESS_CODE = 0;
    private final static Logger logger = LoggerFactory.getLogger(Message.class);
    @ApiModelProperty(value = "响应码", dataType = "Integer", example = "0")
    private int code;
    @ApiModelProperty(value = "响应信息", dataType = "String", example = "操作成功")
    private String msg;
    private T data;
    /**
     * 国际化资源文件集
     */
    private static Map<String, List<ResourceBundle>> RESOURCES = new HashMap<>();
    private static Set<String> resource_names = new HashSet<>();

    /**
     * 初始化国际化文件
     *
     * @param locale 语言
     */
    private static void init(Locale locale) {
        if (resource_names.isEmpty()) {
            String pkg = "i18n";
            ClassLoader classLoader = Message.class.getClassLoader();
            try {
                Enumeration<URL> urls = classLoader.getResources(pkg);
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    // 得到协议的名称
                    String protocol = url.getProtocol();
                    // 如果是以文件的形式保存在服务器上
                    if ("file".equals(protocol)) {
                        // 获取包的物理路径
                        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        // 以文件的方式扫描整个包下的文件 并添加到集合中
                        findClassesByFile(filePath);
                    } else if ("jar".equals(protocol)) {
                        // 如果是jar包文件 获取jar
                        JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        //扫描jar包文件 并添加到集合中
                        findClassesByJar(pkg, jar);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (RESOURCES.isEmpty() || RESOURCES.get(locale.toString()) == null) {
            List<ResourceBundle> resources = new ArrayList<>();
            logger.debug("加载国际化文件:[i18n/messages]");
            ResourceBundle root = ResourceBundle.getBundle("i18n/messages", locale);
            try {
                for (String name : resource_names) {
                    logger.debug("加载国际化文件:[i18n/" + name + "/messages]");
                    ResourceBundle resource = ResourceBundle.getBundle("i18n/" + name + "/messages", locale);
                    if (resource != null) {
                        resources.add(resource);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            resources.add(root);
            RESOURCES.put(locale.toString(), resources);
            //System.out.println(url);
        }
    }

    /**
     * 根据code构造对象
     *
     * @param code 信息码
     */
    private Message(int code) {
        this.code = code;
        this.msg = Message.getMessage(code);
        if (SUCCESS_CODE == this.code && StringUtils.isEmpty(this.msg)) {
            this.msg = "操作成功";
        }
    }

    /**
     * 根据信息码及信息构造对象
     *
     * @param code 代码
     * @param msg  信息
     */
    private Message(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据信息码及信息构造对象
     *
     * @param errorCode 代码
     * @param parameter 参数
     */
    @Deprecated
    public Message(BaseErrorCode errorCode, String... parameter) {
        super();
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg(parameter);
    }

    /**
     * 根据码值与实体对象构造
     *
     * @param errorCode 码值
     * @param data      实体对象
     */
    private Message(CommonErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        if (SUCCESS_CODE == this.code && StringUtils.isEmpty(this.msg)) {
            this.msg = "操作成功";
        }
        this.data = data;
    }

    /**
     * 静态构造成功信息
     *
     * @return 返回成功对象
     */
    public static <T> Message<T> success() {
        return new Message<>(SUCCESS_CODE);
    }

    /**
     * 根据实体对象构造成功信息
     *
     * @param data 实体对象
     * @return 成功信息
     */
    public static <T> Message<T> success(T data) {
        return new Message<>(CommonErrorCode.SUCCESS, data);
    }

    /**
     * 根据码值静态构造失败信息
     *
     * @param errorCode 码值
     * @return 失败信息
     */
    public static <T> Message<T> failure(BaseErrorCode errorCode, String... parameter) {
        return new Message<>(errorCode.getCode(), errorCode.getMsg(parameter));
    }

    /**
     * 静态构造失败信息
     *
     * @param code    码值
     * @param message 信息
     * @return 错误信息
     */
    @Deprecated
    public static <T> Message<T> failure(int code, String message) {
        return new Message<>(code, message);
    }

    /**
     * 根据码值获取信息
     *
     * @param code 码值
     * @return 信息
     */
    public static String getMessage(int code) {
        return getMessage(code, null);
    }

    /**
     * 带默认值获取信息
     *
     * @param code           码值
     * @param defaultMessage 默认信息
     * @return 信息
     */
    public static String getMessage(int code, String defaultMessage) {
        try {
            Locale locale = null;
            String local = SystemUtil.getLocale();
            if (StringUtils.isNotEmpty(local) && local.contains(Global.CHARACTER_UNDERLINE)) {
                locale = new Locale(local.split(Global.CHARACTER_UNDERLINE)[0],
                        local.split(Global.CHARACTER_UNDERLINE)[1]);
            }
            if (locale == null) {
                locale = new Locale("zh", "CN");
            }
            init(locale);
            List<ResourceBundle> list = RESOURCES.get(locale.toString());
            for (ResourceBundle resource : list) {
                String message;
                try {
                    message = resource.getString(String.valueOf(code));
                } catch (MissingResourceException e) {
                    continue;
                }
                if (StringUtils.isNotEmpty(message)) {
                    return message;
//                    return new String(message.getBytes("ISO-8859-1"), "UTF-8");
                }
            }
            return defaultMessage;
        } catch (MissingResourceException e) {
            logger.debug("message:[" + code + "]not found!");
            if (defaultMessage != null) {
                return defaultMessage;
            } else {
                return String.valueOf(code);
            }
        }
    }

    /**
     * @return 返回码值
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回实体对象
     *
     * @return 返回实体对象
     */
    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @param args 参数
     */
    public static void main(String[] args) {
        // System.out.println(Message.success());
    }

    /**
     * 从目录里获取国际化文件夹（子模块）
     *
     * @param pkgPath 包路径
     */
    private static void findClassesByFile(String pkgPath) {
        File dir = new File(pkgPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] children = dir.listFiles();
            if (children != null && children.length > 0) {
                for (File f : children) {
                    if (f.isDirectory()) {
                        resource_names.add(f.getName());
                    }
                }
            }
        }
    }

    /**
     * 从jar里获取国际化文件夹（子模块）
     *
     * @param pkgName 包名
     * @param jar     jar文件
     */
    private static void findClassesByJar(String pkgName, JarFile jar) {
        //String pkgDir = pkgName.replace(".", "/");
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entry = jar.entries();
        JarEntry jarEntry;
        String name;
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文
            jarEntry = entry.nextElement();
            name = jarEntry.getName();
            logger.debug("=====从jar包中扫描国际化文件目录====>>>{}", name);
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (jarEntry.isDirectory() && name.startsWith(pkgName) && name.indexOf("/") > 0) {
                String[] args = name.split("\\/");
                if (args.length > 1) {
                    resource_names.add(args[1]);
                }
            }
        }
        logger.debug("=====从jar包中扫描国际化文件目录结果====>>>,{}", resource_names);
    }

    /**
     * 加载类
     *
     * @param fullClzName 类全名
     * @return 类
     */
    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}")
                .add("\"code\":" + code)
                .add("\"msg\":\"" + msg + "\"");
        if (data != null) {
            sj.add("\"data\":" + JSON.toJSONString(data));
        }

        return sj.toString();
    }
}
