package dao;


import domain.Person;
import org.apache.ibatis.session.SqlSession;
import util.MyFactory;

import java.util.List;

public class PersonDao {

    SqlSession sqlSession = MyFactory.getSqlSession();

    //增
    public int insertPerson(Person person){
        return sqlSession.insert("insertPerson",person);
    }

    //删
    public int deletePerson(String id){
        return sqlSession.delete("deletePerson",id);
    }

    //改
    public int updatePerson(Person person){
        return sqlSession.update("updatePerson",person);
    }

    //查
    public Person selectOnePerson(String id){
        return sqlSession.selectOne("selectOnePerson",id);
    }

    //倒序查询所有
    public List<Person> selectListPerson(String flag){
        return sqlSession.selectList("selectListPerson",flag);
    }
}
