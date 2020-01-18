package com.ziyiou.service;

import com.ziyiou.po.User;

/**
 * @author ziyiou
 */
public interface UserService {
    /**
     * 对登录用户进行检查
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户对象或Null
     */
    User checkUser(String username, String password);
}
