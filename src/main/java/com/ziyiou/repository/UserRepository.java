package com.ziyiou.repository;

import com.ziyiou.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ziyiou
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 跟据username和password查询用户是否存在
     * @param username 用户名
     * @param password 密码
     * @return 查询到的用户
     */
    User findByUsernameAndPassword(String username, String password);
}
