package dao;

import domain.Student;
import org.apache.ibatis.session.SqlSession;
import util.MyFactory;

import java.util.ArrayList;
import java.util.List;

public class StuAndCardDao {

    SqlSession sqlSession = MyFactory.getSqlSession();

    public Student selectOneStu(int id){
        return sqlSession.selectOne("selectOneStu",id);
    }

    public List<Student> selectAllStu(){
         return sqlSession.selectList("selectAllStu");
    }

    public Student selectOneS(int id){
        return sqlSession.selectOne("selectOneS",id);
    }

    public List<Student> selectAllS(){
        return sqlSession.selectList("selectAllS");
    }

}
