package cn.bijiatao.mapstruct.model;

import lombok.Data;

/**
 * 地址
 *
 * @author bijiatao
 */
@Data
public class Address {
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;
}
