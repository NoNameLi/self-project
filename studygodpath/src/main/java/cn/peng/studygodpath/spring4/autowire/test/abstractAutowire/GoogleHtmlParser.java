package cn.peng.studygodpath.spring4.autowire.test.abstractAutowire;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class GoogleHtmlParser extends AbstractHtmlParser {

    @Override
    public List<String> parse() {
        hibernateDao.save(new Object());
        return null;
    }

}