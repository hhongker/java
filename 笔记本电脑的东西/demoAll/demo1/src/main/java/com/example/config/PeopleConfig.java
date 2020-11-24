package com.example.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author hhr
 * @date 2020-06-17 13:39
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Configuration
@ConfigurationProperties(prefix="people")
@PropertySource("classpath:config/config.yml")
public class PeopleConfig {

    private Integer id;
    private String name;
//    private String sex;
    private String birthday;
    private String createTime;
}
