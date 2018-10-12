package cn.peng.studygodpath.springboot.data.mysql.repository;


import cn.peng.studygodpath.springboot.data.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by remote on 2018/3/5.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByNameLike(String name);
    User readByName(String name);
    List<User> getByCreateDateLessThan(Date start);

}
