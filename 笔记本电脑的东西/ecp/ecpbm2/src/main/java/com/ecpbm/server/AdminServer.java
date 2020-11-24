package com.ecpbm.server;

import com.ecpbm.bean.Admin;
import com.ecpbm.dao.AdminDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hhr
 * @date 2020-06-20 11:50
 * @description:
 */
@Service
public class AdminServer {
    @Autowired
    AdminDao adminDao;

    public Admin selectAdminOne(String name, String pwd){
        return adminDao.selectAdminOne(name,pwd);
    }
}
