package cn.peng.studygodpath.java8.lambda.streamplay;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by remote on 2018/3/17.
 */
public interface listStreamInterface {

    default String getProdutObjKey(){
        return "productObj";
    }

    default int getDataSize(){
        return 10;
    }

    default String getProducerDataClassKey(){
        return "dataClass";
    }

    default List<Map<String, Object>> getInit(MapDataProducerInterface producer) {

        List<Map<String, Object>> list = Lists.newArrayList();
        for (int i = 0; i < getDataSize(); i++) {
            Map<String, Object> map = Maps.newHashMap();
            map.put(getProdutObjKey(), producer.production(i));
            producer.getClass().getGenericInterfaces();
            map.put(getProducerDataClassKey(),producer.getTClass());
            list.add(map);
        }

        return list;
    }
}
