package com.exp.server;

import com.exp.bean.People;
import com.exp.mapper.PeopleMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hhr
 * @date 2020-06-14 10:07
 * @description:
 */
@Service
public class PeopleServer {

    @Autowired
    PeopleMapper peopleMapper;

    public People selectPeoPleOne(int i){

        People p = peopleMapper.selectPeopleOne(i);
        System.out.println(p);
        return p;
    }

    public List<People> selectPeoPleList(){
        return peopleMapper.selectPeopleList();
    }
}
