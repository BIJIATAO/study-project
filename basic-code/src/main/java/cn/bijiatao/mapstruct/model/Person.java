package cn.bijiatao.mapstruct.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
     * 身份证
     */
    private String idCard;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    /**
     * 爱好
     */
    private List<String> hobbyList;

    /**
     * 描述
     */
    public static String description;
}
