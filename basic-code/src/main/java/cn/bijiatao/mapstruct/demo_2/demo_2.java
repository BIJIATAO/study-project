package cn.bijiatao.mapstruct.demo_2;

import cn.bijiatao.mapstruct.model.Address;
import cn.bijiatao.mapstruct.model.Person;
import cn.bijiatao.mapstruct.model.PersonDTO;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * mapstruct 基本代码
 * @author bijiatao
 */
@Slf4j
public class demo_2 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("张三");
        person.setAge("18");
        person.setSex("男");
        person.setIdCard("110101199001011234");
        Address address = new Address();
        address.setProvince("广东省");
        address.setCity("深圳市");
        address.setArea("南山区");
        person.setAddress(address);
        person.setBirthday(LocalDateTime.now());
        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("看电影");
        hobbyList.add("听音乐");
        person.setHobbyList(hobbyList);
        Person.description = "这是Person描述";

        PersonDTO convertorPerson = PersonMapper.INSTANCE.toPersonDTO(person);

        log.info("source-person: {}", person);
        log.info("target-personDTO: {}", convertorPerson);

        log.info("----因为对象赋值的是引用，而不是深copy，如下------");

        person.getAddress().setProvince("河北省");
        log.info("source-person: {}", person);
        log.info("target-personDTO: {}", convertorPerson);
    }
}
