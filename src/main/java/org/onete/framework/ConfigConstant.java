package org.onete.framework;

/**
 * 提供相关配置项常量
 *
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/11.
 */
public interface ConfigConstant {
    String CONFIG_FILE = "onete.properties";

    String JDBC_DRIVER = "onete.framework.jdbc.driver";
    String JDBC_URL = "onete.framework.jdbc.url";
    String JDBC_USERNAME = "onete.framework.jdbc.username";
    String JDBC_PASSWORD = "onete.framework.jdbc.password";

    String APP_BASE_PACKAGE = "onete.framework.app.base_package";
    String APP_JSP_PATH = "onete.framework.app.jsp_path";
    String APP_ASSET_PATH = "onete.framework.app.asset_path";
}
