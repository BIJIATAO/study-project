package cn.bijiatao.aop.annotation;

import org.springframework.stereotype.Service;

/**
 * 用户服务类
 *
 * @author bijiatao
 */
@Service
@MyClassAnnotation("用户服务类")
public class UserLoginService {
    // public方法，带方法注解和参数注解
    @MyMethodAnnotation(desc = "用户登录方法", version = 2)
    public String login(
            @MyParamAnnotation(required = true) String username,
            @MyParamAnnotation(required = true) String password,
            @MyParamAnnotation(required = true) String email
    ) {
        // 业务逻辑
        return "登录成功：" + username;
    }

    // public无参方法
    public void logout() {
        System.out.println("用户退出");
    }
}