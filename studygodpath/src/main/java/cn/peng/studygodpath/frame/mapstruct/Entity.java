package cn.peng.studygodpath.frame.mapstruct;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-16 15:49
 * QDescription:
 */
@Data
@Builder
public class Entity implements Serializable {
    private byte state1;
    private char luckyChar1;
    private short day1;
    private int stock1;
    private long timestamp1;
    private float money1;
    private double highMoney1;
    private boolean test1;

    private Long id1;
    private LocalDateTime gmtCreate1;
    private LocalDate createTime1;
    private Long buyerId1;
    private int age1;
    private String userNick1;
    private String userVerified1;
    private BigDecimal amount1;
    private Boolean deleteFlag1;
    private List<String> item1;
    private String ignore;
    private Map<String, Object> obj;
}
