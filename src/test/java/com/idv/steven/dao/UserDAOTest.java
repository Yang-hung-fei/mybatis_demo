package com.idv.steven.dao;

import com.idv.steven.utils.MybatisUtils;
import com.idv.steven.vo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Test
    public void testInsertUser() {
        User user = new User(0, "zansea", "123123", "張三", "01.jpg");

        UserDAO userDAO = MybatisUtils.getMapper(UserDAO.class);
        int i = userDAO.insertUser(user);
        System.out.println(i);
    }
}