package cn.peng.minispring.service;

import cn.peng.minispring.annotation.Qualifier;
import cn.peng.minispring.annotation.Service;
import cn.peng.minispring.dao.TestDao;

@Service
public class TestService {

    @Qualifier
    private TestDao testDao;

    public void baseTest(){
        System.out.println("service test succes");
        testDao.test();
    }
}
