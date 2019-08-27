package com.java.char_room.client.dao;

import com.java.char_room.client.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class AccountDaoTest {

    private AccountDao accountDao=new AccountDao();//对象
    @Test
    public void userReg() {
        User user=new User();
        user.setUserName("test");
        user.setPassword("123");
        user.setBrief("帅");
        boolean flag=accountDao.userReg(user);
        Assert.assertTrue(flag);
    }

    @Test
    public void userLogin() {
        String userName="test";
        String password="123";
        User user=accountDao.userLogin(userName,password);
        System.out.println(user);
        Assert.assertNotNull(user);
    }
}