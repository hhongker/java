package dao;

import domain.Student2;
import org.apache.ibatis.annotations.Select;

public interface StudentDao {

    @Select("select * from students where id = #{id}")
    Student2 selectStudentOne(Integer id);

}
