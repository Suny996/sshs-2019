package com.sshs.core.util;

import com.sshs.core.constant.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;

/**
 * 全局配置文件处理类
 *
 * @author Suny
 * @date 2017-10-23
 */
@Component
public class Configure {
    private static final Logger logger = LoggerFactory.getLogger(Configure.class);
    @Resource
    Environment env;
    /**
     * 是否缓存视图文件配置项key
     */
    public static final String CONFIGURE_CACHED_FLAG = "core.view.cached";

    /**
     * 是否缓存视图文件配置项no Cache
     */
    public static final String CONFIGURE_CACHED_NOCACHE = "false";

    /**
     * 运行模式配置项目key
     */
    public static final String CONFIGURE_RUNMOD_FLAG = "core.server.runMode";
    /**
     * 运行模式配置项目run
     */
    public static final String CONFIGURE_RUNMOD_RUN = "run";
    /**
     * 运行模式配置项目debug
     */
    public static final String CONFIGURE_RUNMOD_DEBUG = "debug";

    /**
     * 视图模板文件路径配置项目key
     */
    public static final String CONFIGURE_VIEW_TEMPLATE_HTML = "core.view.html.template.path";

    public String getProperty(String key) {
        return env.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return env.getProperty(key, defaultValue);
    }

    /**
     * 获取classpath1
     *
     * @return
     */
    public static String getClasspath() {
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
                .replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(Global.CHARACTER_COLON) != 1) {
            path = File.separator + path;
        }
        if (path != null && path.endsWith(Global.CHARACTER_SPRIT)) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    /**
     * 获取classpath2
     *
     * @return
     */
    public String getClassResources() {
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
                .replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(Global.CHARACTER_COLON) != 1) {
            path = File.separator + path;
        }
        return path;
    }

    /**
     * 获取classpath下的指定文件URL
     *
     * @param fileName
     * @return
     */
    public URL getClassPathFileUrl(String fileName) {
        /**
         * getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件
         * 的绝对路径
         */
        URL url = Configure.class.getResource(fileName);
        return url;
    }

    /**
     * 获取classpath下的指定文件
     *
     * @param fileName
     * @return
     */
    public File getClassPathFile(String fileName) {
        URL url = getClassPathFileUrl(fileName);
        if (url == null) {
            logger.error("文件：[" + fileName + "]不存在！！！");
            return null;
        }
        File file = new File(url.getFile());
        return file;
    }

    /**
     * 获取classpath下的指定文件的绝对路径
     *
     * @param fileName
     * @return
     */
    public String getClassPathFileName(String fileName) {
        URL url = getClassPathFileUrl(fileName);
        if (url == null) {
            logger.error("文件：[" + fileName + "]不存在！！！");
            return null;
        }
        return url.getFile();
    }

    /**
     * 获取classpath下的指定文件的绝对路径
     *
     * @param fileName
     * @return
     */
    public String getClassPathFileShortName(String fileName) {
        File file = getClassPathFile(fileName);
        return file.getName();
    }

    /**
     * 获取classpath下的指定文件所在目录
     *
     * @param fileName
     * @return
     */
    public File getClassPathFileDir(String fileName) {
        File path = null;
        File file = getClassPathFile(fileName);
        if (file == null) {
            logger.error("文件：[" + fileName + "]不存在！！！");
            return null;
        }
        if (file.exists()) {
            path = file.getParentFile();
        }
        return path;
    }

    /**
     * 获取classpath下的指定文件所在目录名
     *
     * @param fileName
     * @return
     */
    public String getClassPathFileDirName(String fileName) {
        File file = getClassPathFileDir(fileName);
        if (file == null) {
            logger.error("文件：[" + fileName + "]不存在！！！");
            return null;
        }
        return file.getName();
    }
}
