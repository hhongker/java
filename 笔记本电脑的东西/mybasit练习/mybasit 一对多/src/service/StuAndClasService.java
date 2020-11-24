package service;

import dao.StuAndClasDao;
import domain.Clas;
import util.MySpring;

import java.util.List;

public class StuAndClasService {
    StuAndClasDao stuAndClasDao = MySpring.myObj("dao.StuAndClasDao");

    public Clas selectOneClas(int id){
        return stuAndClasDao.selectOneClas(id);
    }

    public List<Clas>selectAllClas(){
        return stuAndClasDao.selectAllClas();
    }

    public Clas selectOneC(int id){
        return stuAndClasDao.selectOneC(id);
    }

    public List<Clas> selectAllC(){
        return stuAndClasDao.selectAllC();
    }

}
