package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BasitFactory {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configuration.xml")
        );
    }

    public static SqlSession getSqlSession(){
        return getSqlSession(true);
    }
    public static SqlSession getSqlSession(boolean flag){
        return sqlSessionFactory.openSession(flag);
    }

}
