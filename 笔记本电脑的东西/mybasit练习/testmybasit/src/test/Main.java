package test;

import domain.Class;
import domain.Course;
import org.apache.ibatis.session.SqlSession;

public class Main {
    static SqlSession sqlSession = util.BasitFactory.getSqlSession();

    public static Class selecClassOne(int id){
        return sqlSession.selectOne("selecClassOne",id);
    }
    public static Class selectClassOne(int id){
        return sqlSession.selectOne("selectClassOne",id);
    }

    public static Course selectCourseOne(int id){
        return sqlSession.selectOne("selectCourseOne",id);
    }


    public static Class selectClassOneForStudent(int id){
        return sqlSession.selectOne("selectClassOneForStudent",id);
    }
    public static void main(String[] args) {
        System.out.println(selectCourseOne(1));
    }
}
