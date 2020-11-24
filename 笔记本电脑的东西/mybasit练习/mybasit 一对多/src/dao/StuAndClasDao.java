package dao;

import domain.Clas;
import org.apache.ibatis.session.SqlSession;
import util.MyFactory;

import java.util.List;

public class StuAndClasDao {

    SqlSession sqlSession = MyFactory.getSqlSession();

    public Clas selectOneClas(int id){
        return sqlSession.selectOne("selectOneClas",id);
    }

    public List<Clas> selectAllClas(){
        return sqlSession.selectList("selectAllClas");
    }

    public Clas selectOneC(int id){
        return sqlSession.selectOne("selectOneC",id);
    }
    public List<Clas> selectAllC(){
        return sqlSession.selectList("selectAllC");
    }
}
