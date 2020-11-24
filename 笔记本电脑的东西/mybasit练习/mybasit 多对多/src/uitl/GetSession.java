package uitl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSession {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("configuration.xml")
        );
    }

    public static SqlSession getSqlSessionFactory() {
        return getSqlSessionFactory(true);
    }

    public static SqlSession getSqlSessionFactory(boolean flag) {
        return sqlSessionFactory.openSession(flag);
    }
}
