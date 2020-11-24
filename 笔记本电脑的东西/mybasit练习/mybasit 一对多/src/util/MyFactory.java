package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyFactory {

    private static SqlSessionFactory factory;
    static {
        factory = new SqlSessionFactoryBuilder().build(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.xml")
        );
    }

    public static SqlSession getSqlSession(){
        return getSqlSession(true);
    }

    public static SqlSession getSqlSession(boolean flag){
        return factory.openSession(flag);
    }

 }
