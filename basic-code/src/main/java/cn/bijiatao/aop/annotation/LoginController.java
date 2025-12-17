package cn.bijiatao.aop.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author bijiatao
 */
@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "email", required = false) String email) {
        return userLoginService.login(username, password, email);
    }

    @PostMapping("/logout")
    public void logout() {
        userLoginService.logout();
    }
}
