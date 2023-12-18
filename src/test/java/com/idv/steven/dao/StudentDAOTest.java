package com.idv.steven.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idv.steven.mapper.StudentMapperAnno;
import com.idv.steven.utils.MybatisUtils;
import com.idv.steven.vo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.junit.Assert.*;

public class StudentDAOTest {

    @org.junit.Test
    public void testInsertStudent() {
        //1.當我們獲取sqlSession物件時，就默認開啟了事務
            //getSqlSession()已封裝false，預設開啟事務管理
//            SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentMapperAnno studentDAO = MybatisUtils.getMapper(StudentMapperAnno.class);
            //測試是否有獲得 DAO 物件
//           System.out.println(studentDAO);
            //測試DAO中的方法
            Student student = new Student(0, "1016", "張三", "男", 22);
            int i = studentDAO.insertStudent(student);
            //操作1
            //操作2
            //操作3
            System.out.println(student);
            //2.操作完成，後須手動提交
//            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            //3.當操作出現異常調用rollback進行回滾
//            sqlSession.rollback();
        }
    }
    //假設delete不須事務管理，getMapper()自動commit為true
    @Test
    public void testDeleteStudent() {
            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
            StudentDAO studentDAO = MybatisUtils.getMapper(StudentDAO.class);
            int i = studentDAO.deleteStudent("1001");
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
        //不須事務管理直接取得DAO物件
        StudentMapperAnno studentDAO = MybatisUtils.getMapper(StudentMapperAnno.class);
        //測試DAO中的方法
        List<Student> list = studentDAO.listStudents();
        assertNotNull(list);
        for(Student stu: list){
            System.out.println(stu);
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
            StudentMapperAnno studentDAO = sqlSession.getMapper(StudentMapperAnno.class);

            //測試DAO中的方法
            Student student = studentDAO.queryStudent("1010");

            System.out.println(student);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //分頁查詢(PageHelper插件版)
    @Test
    public void testListStudentsByPage(){
        StudentDAO studentDAO = MybatisUtils.getMapper(StudentDAO.class);
        //執行查詢前創建PageHelper的攔截器，將分頁訊息設置給sqlSession
        PageHelper.startPage(1, 10);
        List<Student> students = studentDAO.listStudentsByGender("男");
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        List<Student> list = pageInfo.getList();
        //PageInfo 對數據進行封裝的物件，包含了頁數等屬性
    }

//    @Test
//    public void testListStudentsByPage(){
//        try {
//            //用流讀取mybatis 配置文件
//            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//
//            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
//            SqlSessionFactory factory =builder.build(is);
//
//            //取得DB Session連接物件:通過工廠方法設計模式
//            SqlSession sqlSession = factory.openSession();
//
//            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
//            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
//            //方法2 Map
////            HashMap<String,Integer> hashMap= new HashMap<String,Integer>();
////            hashMap.put("start", 0);
////            hashMap.put("pageSize", 1);
//            //測試DAO中的方法
//            List<Student> list = studentDAO.listStudentsByPage(0,1);
//
//            System.out.println(list);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    public void testGetCount(){
//        try {
//            //用流讀取mybatis 配置文件
//            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//
//            //SqlSessionFactory表示mybatis的Session工廠(參數類型InputStream)
//            SqlSessionFactory factory =builder.build(is);
//
//            //取得DB Session連接物件:通過工廠方法設計模式
//            SqlSession sqlSession = factory.openSession();
//
//            //通過SqlSession物件調用getMapper方法獲得 DAO 介面物件，利用<動態代理>取得物件
//            StudentDAO studentDAO = sqlSession.getMapper(StudentDAO.class);
//            int count = studentDAO.getCount();
//
//            assertEquals(3, count);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}