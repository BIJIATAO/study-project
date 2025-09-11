package cn.bijiatao.mapstruct.model;


import lombok.Data;

/**
 * source类
 *
 * @author bijiatao
 */
@Data
public class Person {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private Address address;

    /**
     * 描述
     */
    public static String description;
}
