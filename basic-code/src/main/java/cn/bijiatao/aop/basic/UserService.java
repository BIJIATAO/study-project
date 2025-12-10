package cn.bijiatao.aop.basic;


public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
}