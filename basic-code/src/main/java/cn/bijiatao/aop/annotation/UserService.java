package cn.bijiatao.aop.annotation;

/**
 * 用户服务类
 * @author bijiatao
 */
@MyClassAnnotation("用户服务类")
public class UserService {

    // public方法，带方法注解和参数注解
    @MyMethodAnnotation(desc = "用户登录方法", version = 2)
    public String login(
            @MyParamAnnotation(required = true) String username,
            @MyParamAnnotation(required = true) String password
    ) {
        return "登录成功：" + username;
    }

    // private方法
    private void validatePassword(String password) {
        if (password.length() < 6) {
            throw new RuntimeException("密码长度不足六位，请重新输入");
        }
    }

    // public无参方法
    public void logout() {
        System.out.println("用户退出");
    }
}