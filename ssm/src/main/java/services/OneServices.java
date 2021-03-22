package services;

import domain.User;
import mapper.OneMapper;
import mapper.OneMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneServices {

    @Autowired
    OneMapper oneMapper;


    @Autowired
    OneMapper2 oneMapper2;

    public User oneServices1(){
        System.out.println("oneServices");
        return oneMapper.selectUserOne();
    }


    public User oneServices2() {
        System.out.println(222);
        return oneMapper2.selectUserOne();
    }
}
