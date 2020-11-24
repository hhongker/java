package com.ecpbm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hhr
 * @date 2020-06-20 11:46
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {

    private Long id;
    private String name;
    private String pwd;
}
