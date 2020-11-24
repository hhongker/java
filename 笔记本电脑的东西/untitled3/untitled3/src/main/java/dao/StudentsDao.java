package dao;


import bean.Students;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author hhr
 * @date 2020-07-05 14:34
 * @description:
 */

public interface StudentsDao {

    @Select("SELECT * FROM test1.students where id = #{id};")
    Students selectPeopleOne(@Param("id") int id);
}
