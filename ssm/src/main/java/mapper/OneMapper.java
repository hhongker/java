package mapper;

import domain.User;
import org.apache.ibatis.annotations.Select;


public interface OneMapper {

    @Select("select * from user where age = 12")
    public User selectUserOne();
}
