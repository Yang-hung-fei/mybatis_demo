package com.idv.steven.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory factory;//單例
    private static final ThreadLocal<SqlSession> Local = new ThreadLocal<>();

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory =builder.build(is);//相當於 return new DefaultSqlSessionFactory(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSessionFactory(){
        return factory;
    }

    public static SqlSession getSqlSession(){
        return getSqlSession(false);
    }

    //利用sqlSession達成事務管理
    private static SqlSession getSqlSession(boolean isAutoCommit){
        SqlSession sqlSession = Local.get();
        if(sqlSession==null){
            sqlSession = factory.openSession(isAutoCommit);//if true 自動commit
            Local.set(sqlSession);//事務管理ThreadLocal
        }
        return sqlSession;
    }
    //getMapper提供給不須事務管理的方法取得DAO物件
    public static <T extends Object> T getMapper(Class<T> c){
        return getSqlSession(true).getMapper(c);
    }
}
