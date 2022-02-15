package cn.peng.webflux.repository;

import cn.peng.webflux.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private AtomicLong count = new AtomicLong();
    private Map<Long, User> userRepository = new HashMap<>();

    public UserRepository() {
        long l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小明", 10));

        l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小红", 9));

        l = count.incrementAndGet();
        userRepository.put(l, new User(l, "小鹏", 8));
    }


    public User getById(Long id) {
        return userRepository.get(id);
    }

    public Collection<User> listAll() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return userRepository.values();
    }

    public void createUser(User user) {
        Long id = count.incrementAndGet();
        user.setId(id);
        userRepository.put(id, user);
    }

    public void updateUser(User user) {
        Optional.ofNullable(userRepository.get(user.getId())).ifPresent(t -> t.setAge(user.getAge()).setName(user.getName()));
    }
}
