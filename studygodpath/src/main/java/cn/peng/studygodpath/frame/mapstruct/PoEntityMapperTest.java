package cn.peng.studygodpath.frame.mapstruct;

import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-16 15:56
 * QDescription:
 */
public class PoEntityMapperTest {

    @Test
    public static void testNormal() {
        PO userPo = PO.builder()
                .state((byte) 1).luckyChar('A').day((short) 10)
                .stock(11).timestamp(System.currentTimeMillis())
                .money(10000.1f).highMoney(10000.0811).test(false)
                .id(1L)
                .gmtCreate("2023-02-16 17:44:12")
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age("18.50")
                .amount(BigDecimal.valueOf(10000.0811))
                .deleteFlag(Boolean.FALSE)
                .item(Lists.newArrayList("a", "b"))
                .obj("{\"id\":2,\"name\":\"测试123\"}")
                .build();
        System.out.println("PO:" + userPo);
        Entity userEntity = PoEntityMapper.INSTALL.toEntity(userPo);
        System.out.println("Entity:" + userEntity);
    }
}
