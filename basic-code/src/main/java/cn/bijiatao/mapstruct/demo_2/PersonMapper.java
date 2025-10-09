package cn.bijiatao.mapstruct.demo_2;

import cn.bijiatao.mapstruct.model.Person;
import cn.bijiatao.mapstruct.model.PersonDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mapstruct 映射
 *
 * @author bijiatao
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(source = "birthday", target = "myBirthday", qualifiedByName = "toStringBirthday"),
            @Mapping(source = "idCard", target = "idNo")
            // mapstruct也不支持静态变量转换，如果加了下一行代码，会抛异常
            // @Mapping(target = "description", source = "description"),
    })
    PersonDTO toPersonDTO(Person person);

    @Named("toStringBirthday")
    default String toStringBirthday(LocalDateTime birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return birthday.format(formatter);
    }
}