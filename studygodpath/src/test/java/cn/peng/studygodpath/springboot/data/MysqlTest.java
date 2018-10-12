package cn.peng.studygodpath.springboot.data;

import cn.peng.studygodpath.springboot.data.mysql.config.JpaConfiguration;
import cn.peng.studygodpath.springboot.data.mysql.entity.Department;
import cn.peng.studygodpath.springboot.data.mysql.entity.Role;
import cn.peng.studygodpath.springboot.data.mysql.entity.User;
import cn.peng.studygodpath.springboot.data.mysql.repository.DepartmentRepository;
import cn.peng.studygodpath.springboot.data.mysql.repository.RoleRepository;
import cn.peng.studygodpath.springboot.data.mysql.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){

        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        org.springframework.util.Assert.notNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        org.springframework.util.Assert.notNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDeparment(department);

        List<Role> roles = roleRepository.findAll();
        org.springframework.util.Assert.notNull(roles);
        user.setRole(roles);

        userRepository.save(user);
        org.springframework.util.Assert.notNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        org.springframework.util.Assert.notNull(page);
        for(User user : page.getContent()) {
            logger.info("====user==== user name:{}, department name:{}, role name:{}",
                    user.getName(), user.getDeparment().getName(), user.getRole().get(0).getName());
        }
    }

    //@Test
    public void test(){
        User user1 = userRepository.findByNameLike("u%");
        org.springframework.util.Assert.notNull(user1);

        User user2 = userRepository.readByName("user");
        org.springframework.util.Assert.notNull(user2);

        List<User> users = userRepository.getByCreateDateLessThan(new Date());
        org.springframework.util.Assert.notNull(users);
    }
}
