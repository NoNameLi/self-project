package cn.peng.studygodpath.spring4.aop.service.impl;

import cn.peng.studygodpath.spring4.aop.service.BusinessService;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Override
    public String business(String str) {
        System.out.println("exec business");
        return "exec finish";
    }


}
