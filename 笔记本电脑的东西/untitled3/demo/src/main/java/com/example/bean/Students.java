package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hhr
 * @date 2020-07-04 14:49
 * @description:
 */
@Data
@AllArgsConstructor@NoArgsConstructor
public class Students {

    private Integer id;
    private String name;
    private String sex;
    private Float math;
    private Float chinese;
    private Float english;

}
