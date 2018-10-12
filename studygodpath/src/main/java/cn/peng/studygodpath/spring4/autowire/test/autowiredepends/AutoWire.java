package cn.peng.studygodpath.spring4.autowire.test.autowiredepends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by remote on 2017/9/28.
 */
@Service("autoWire")
public class AutoWire {

    @Autowired
    public TestService test1ServiceImpl;

//    @Autowired
//    public TestService testServiceImpl2;
}
