package com.example.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @author hhr
 * @date 2020-06-17 15:47
 * @description:
 */
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "vegetables")
@PropertySource("classpath:config/config.properties")
public class VegetablesConfig {
    private Integer id;
    private String name;
    //    private String sex;
    private String birthday;
    private String createTime;
}
