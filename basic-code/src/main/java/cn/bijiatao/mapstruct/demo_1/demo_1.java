package cn.bijiatao.mapstruct.demo_1;

import cn.bijiatao.mapstruct.model.Address;
import cn.bijiatao.mapstruct.model.Person;
import cn.bijiatao.mapstruct.model.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * beanutils 的 基础实现
 *
 * @author bijiatao
 */
@Slf4j
public class demo_1 {
    public static void main(String[] args) {
        // 初始化
        Person person = new Person();
        person.setName("张三");
        person.setAge("18");
        person.setSex("男");
        Address address = new Address();
        address.setProvince("广东省");
        address.setCity("深圳市");
        address.setArea("南山区");
        person.setAddress(address);
        Person.description = "这是Person描述";

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("李四");
        personDTO.setAge(81);
        personDTO.setSex('女');
        PersonDTO.description = "这是PersonDTO描述";

        // 赋值
        BeanUtils.copyProperties(person, personDTO);

        log.info("source-person: {}", person);
        log.info("target-personDTO: {}", personDTO);

        log.info("----因为对象赋值的是引用，而不是深copy，如下------");

        person.getAddress().setProvince("河北省");
        log.info("source-person: {}", person);
        log.info("target-personDTO: {}", personDTO);

        log.info("----下面测试静态赋值------");
        log.info(PersonDTO.description);
        log.info(Person.description);
    }
}
