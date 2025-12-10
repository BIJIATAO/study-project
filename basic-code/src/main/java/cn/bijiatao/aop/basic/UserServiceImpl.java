package cn.bijiatao.aop.basic;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    // 模拟数据库存储
    private static final Map<Long, User> USER_DB = new HashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    @Override
    public User createUser(User user) {
        System.out.println("【业务逻辑】正在创建用户...");
        Long newId = ID_GENERATOR.getAndIncrement();
        user.setId(newId);
        USER_DB.put(newId, user);
        System.out.println("【业务逻辑】用户创建成功: " + user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        System.out.println("【业务逻辑】正在查询ID为 " + id + " 的用户...");
        User user = USER_DB.get(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("【业务逻辑】正在删除ID为 " + id + " 的用户...");
        USER_DB.remove(id);
        System.out.println("【业务逻辑】用户删除成功");
    }
}