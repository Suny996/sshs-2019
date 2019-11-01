package com.sshs.core.message;

import com.sshs.core.constant.Global;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Suny
 */
public class Message<T> {
    private final static String SUCCESS_CODE = "000000";
    private final static Logger logger = LoggerFactory.getLogger(Message.class);
    private String code;
    private String msg;
    T data;
    /**
     * 国际化资源文件集
     */
    private static Map<String, List<ResourceBundle>> RESOURCES = new HashMap<String, List<ResourceBundle>>();
    private static Set<String> resource_names = new HashSet<>();

    /**
     * 初始化国际化文件
     *
     * @param locale
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
            List<ResourceBundle> resources = new ArrayList<ResourceBundle>();
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

    public Message(String code) {
        this.code = code;
        this.msg = Message.getMessage(code);
        if (SUCCESS_CODE.equals(this.code) && StringUtils.isEmpty(this.msg)) {
            this.msg = "操作成功";
        }
    }

    public Message(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Message(String code, T data) {
        this.code = code;
        this.msg = Message.getMessage(code);
        if (SUCCESS_CODE.equals(this.code) && StringUtils.isEmpty(this.msg)) {
            this.msg = "操作成功";
        }
        this.data = data;
    }

    public static Message success() {
        return new Message(SUCCESS_CODE);
    }

    public static <T> Message<T> success(T data) {
        return new Message<T>(SUCCESS_CODE, data);
    }

    public static Message failure(String code) {
        return new Message(code);
    }

    public static Message failure(String code, String message) {
        return new Message(code, message);
    }

    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    public static String getMessage(String code, String defaultMessage) {
        try {
            Locale locale = null;
            String local = "";//SystemUtil.getLocale();
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
                String message = null;
                try {
                    message = resource.getString(code);
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
                return code;
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
       // System.out.println(Message.success());
    }

    /**
     * 从目录里获取国际化文件夹（子模块）
     *
     * @param pkgPath
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
     * @param pkgName
     * @param jar
     */
    private static void findClassesByJar(String pkgName, JarFile jar) {
        String pkgDir = pkgName.replace(".", "/");
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
}
