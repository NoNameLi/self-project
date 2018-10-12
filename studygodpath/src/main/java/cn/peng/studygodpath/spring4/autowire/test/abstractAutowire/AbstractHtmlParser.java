package cn.peng.studygodpath.spring4.autowire.test.abstractAutowire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractHtmlParser {
    @Autowired
    protected IHibernateDao hibernateDao;

    public void setHibernateDao(IHibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }

    public abstract List<String> parse();
}