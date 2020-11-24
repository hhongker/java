package com.example.bean;

import lombok.*;
import org.hibernate.annotations.Table;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author hhr
 * @date 2020-06-17 13:49
 * @description:
 */
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class People implements Serializable {


    @Id
    private Integer id;
    @Column
    @NonNull
    private String name;

    //    private String sex;
    @Column
    @NonNull
    private String birthday;
    @Column
    @NonNull
    private String createTime;

}