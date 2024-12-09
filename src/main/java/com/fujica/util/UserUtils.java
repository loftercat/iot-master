package com.fujica.util;
import com.fujica.constant.HeaderConstant;
import org.slf4j.MDC;

/**
 * 获取当前登录用户信息
 */
public class UserUtils {

    /**
     * 当前登录用户名
     *
     * @return
     */
    public static String currentUsername() {
        return MDC.get(HeaderConstant.USERNAME);
    }

    /**
     * 当前登录用户ID
     *
     * @return
     */
    public static String currentUserId() {
        return MDC.get(HeaderConstant.USER_ID);
    }
}
