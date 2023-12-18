package com.idv.steven.dao;

import com.idv.steven.utils.MybatisUtils;
import com.idv.steven.vo.Details;
import com.idv.steven.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class UserDAOTest {

    //1:1
    @Test

    public void testInsertUser() {

        //用戶註冊提交了基本訊息&詳情
        User user = new User(0,"steven","123123","飛飛","11.jpg");
        Details details = new Details(0,"桃園","0955148789","我吃飯",0);

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            //存user
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            int i = userDAO.insertUser(user);//主鍵回填
            System.out.println(i);

            details.setUserId(user.getUserId());//取得auto_increment id

            DetailsDAO detailsDAO = sqlSession.getMapper(DetailsDAO.class);
            int j = detailsDAO.insertDetail(details);
            System.out.println(j);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
}