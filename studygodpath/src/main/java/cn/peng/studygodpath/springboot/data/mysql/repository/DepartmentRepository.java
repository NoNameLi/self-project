package cn.peng.studygodpath.springboot.data.mysql.repository;

import cn.peng.studygodpath.springboot.data.mysql.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by remote on 2018/3/5.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{


}
