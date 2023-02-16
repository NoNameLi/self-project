package cn.peng.studygodpath.frame.mapstruct;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PO implements Serializable {
    private byte state;
    private char luckyChar;
    private short day;
    private int stock;
    private long timestamp;
    private float money;
    private double highMoney;
    private boolean test;

    private Long id;
    private String gmtCreate;
    private LocalDate createTime;
    private Long buyerId;
    private String age;
    private String userNick;
    private String userVerified;
    private BigDecimal amount;
    private Boolean deleteFlag;
    private List<String> item;

    private String ignore;
    private String obj;
}
