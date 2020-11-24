package com.exp.mapper;

import com.exp.bean.People;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.*;


/**
 * @author hhr
 * @date 2020-06-14 09:56
 * @description:
 */

public interface PeopleMapper {

    @Select("select * from people where id = #{id}")
    People selectPeopleOne(@Param("id") int id);

    @Select("select * from people")
    List<People> selectPeopleList();

    @Insert("insert into people (name,birthday,createTime) values(#{name},#{birthday},#{createTime})")
    void insertPeopleOne(People people);



}
