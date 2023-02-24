package cn.peng.studygodpath.frame.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-16 15:49
 * QDescription:
 */
@Mapper(uses = CustomerAttrConvert.class, imports = {LocalDate.class})
//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)  使用spring bean管理
public interface PoEntityMapper {
    PoEntityMapper INSTALL = Mappers.getMapper(PoEntityMapper.class);

    @Mapping(source = "state", target = "state1")
    @Mapping(source = "luckyChar", target = "luckyChar1")
    @Mapping(source = "day", target = "day1")
    @Mapping(source = "stock", target = "stock1")
    @Mapping(source = "timestamp", target = "timestamp1")
    @Mapping(source = "money", target = "money1")
    @Mapping(source = "highMoney", target = "highMoney1")
    @Mapping(source = "test", target = "test1")
    @Mapping(source = "id", target = "id1")
    @Mapping(source = "gmtCreate", target = "gmtCreate1", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "createTime", target = "createTime1", defaultExpression = "java(LocalDate.now())")
    @Mapping(source = "buyerId", target = "buyerId1")
    @Mapping(source = "age", target = "age1", numberFormat = "#0.00")
    @Mapping(source = "userNick", target = "userNick1")
    @Mapping(source = "userVerified", target = "userVerified1")
    @Mapping(source = "amount", target = "amount1")
    @Mapping(source = "deleteFlag", target = "deleteFlag1")
    @Mapping(source = "item", target = "item1")
    @Mapping(target = "ignore", ignore = true)
    @Mapping(target = "obj", source = "obj", qualifiedByName = "jsonToObj")
    public Entity toEntity(PO po);

}
