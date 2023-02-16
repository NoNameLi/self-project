package cn.peng.studygodpath.frame.mapstruct;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-16 17:51
 * QDescription:
 */
public class CustomerAttrConvert {

    @Named("jsonToObj")
    public Map<String, Object> jsonToObj(String json) {
        if (StringUtils.isEmpty(json))
            return Collections.emptyMap();
        return (Map<String, Object>) JSON.parseObject(json, Map.class);
    }
}
