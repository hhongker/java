package com.ecpbm.dao;

import com.ecpbm.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author hhr
 * @date 2020-06-20 11:47
 * @description:
 */

public interface AdminDao {
    @Select("select * from admin_info where name = #{name} and pwd = #{pwd}")
    Admin selectAdminOne(@Param("name") String name, @Param("pwd") String pwd);
}
