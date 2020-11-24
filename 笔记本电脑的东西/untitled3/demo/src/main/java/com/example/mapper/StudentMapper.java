package com.example.mapper;

import com.example.bean.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @author hhr
 * @date 2020-07-05 15:20
 * @description:
 */

//@Mapper
//@Repository
public interface StudentMapper {

    @Select("SELECT * FROM test1.students where id = #{id};")
    Students selectPeopleOne(@Param("id") int id);
}
