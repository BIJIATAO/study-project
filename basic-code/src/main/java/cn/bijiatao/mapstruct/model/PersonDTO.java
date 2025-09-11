package cn.bijiatao.mapstruct.model;

import lombok.Data;

/**
 * target类
 *
 * @author bijiatao
 */
@Data
public class PersonDTO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Character sex;

    /**
     * 地址
     */
    private Address address;

    /**
     * 描述
     */
    public static String description;
}
