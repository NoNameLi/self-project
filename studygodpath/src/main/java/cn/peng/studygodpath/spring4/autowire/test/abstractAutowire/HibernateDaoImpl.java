package cn.peng.studygodpath.spring4.autowire.test.abstractAutowire;

/**
 * Created by remote on 2017/11/13.
 */
import org.springframework.stereotype.Repository;

@Repository("hibernateDao")
public class HibernateDaoImpl implements IHibernateDao {

    @Override
    public void save(Object obj) {
        System.out.println("save ok");
    }

}