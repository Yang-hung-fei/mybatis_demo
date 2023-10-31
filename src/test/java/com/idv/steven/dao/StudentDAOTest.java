package com.idv.steven.dao;

import com.idv.steven.vo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOTest {

    @org.junit.Test
    public void testInsertStudent() {

        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //測試是否有獲得 DAO 物件
            System.out.println(studentDAO);
            //測試DAO中的方法
            int i = studentDAO.insertStudent(new Student(0, "1003", "張三", "男", 22));

            //事務管理需要寫
            sqlSession.commit();

            //if >0 成功(如無事務管理(commit)就算為1 DB也不會有任何數據)
            System.out.println(i);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    @Test
    public void testDeleteStudent() {
        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //測試是否有獲得 DAO 物件
            System.out.println(studentDAO);
            //測試DAO中的方法
            int i = studentDAO.deleteStudent("1001");

            //事務管理需要寫
            sqlSession.commit();

            //if >0 成功(如無事務管理(commit)就算為1 DB也不會有任何數據)
            System.out.println(i);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testUpdateStudent(){
        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //測試是否有獲得 DAO 物件
            System.out.println(studentDAO);
            //測試DAO中的方法
            int i = studentDAO.updateStudent(new Student(4,"1001","張s","男",21));

            //事務管理需要寫
            sqlSession.commit();

            assertEquals(1, i);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testListStudents(){
        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //測試DAO中的方法
            List<Student> list = studentDAO.listStudents();

            assertNotNull(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void queryStudent(){
        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);

            //測試DAO中的方法
            Student student = studentDAO.queryStudent("1001");

            System.out.println(student);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testListStudentsByPage(){
        try {
            //用流讀取mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
            SqlSessionFactory factory =builder.build(is);

            //取得DB Session連接物件:通過工廠方法設計模式
            SqlSession sqlSession = factory.openSession();

            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
            //方法2 Map
//            HashMap<String,Integer> hashMap= new HashMap<String,Integer>();
//            hashMap.put("start", 0);
//            hashMap.put("pageSize", 1);
            //測試DAO中的方法
            List<Student> list = studentDAO.listStudentsByPage(0,1);

            System.out.println(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}