package com.example.api.utils;

import java.util.regex.Pattern;

public class CheckUtil {
    /**
     * 检查邮箱格式
     * @param email 邮箱
     * @return 格式是否正确
     */
    public static boolean checkEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            String regex = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
            return Pattern.matches(regex, email);
        }
        return false;
    }

    /**
     * 验证密码格式
     * 密码包含 数字,英文,字符中的两种以上，长度6-20
     * @param password 密码
     * @return 格式是否正确
     */
    public static boolean checkPassword(String password){
        if ((password != null) && (!password.isEmpty())) {
            String regex = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$";
            return Pattern.matches(regex, password);
        }
        return false;
    }
}
