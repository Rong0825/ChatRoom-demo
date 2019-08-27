package com.java.char_room.util;

import com.java.char_room.client.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.*;

public class CommUtilsTest {
//测试资源文件是否正确的加载进来
    @Test
    public void loadProperties() {
        String fileName="datasource.properties";
        Properties properties=CommUtils.loadProperties(fileName);
        //断言
        Assert.assertNotNull(properties);
        //System.out.println(properties);
    }

    @Test
    public void object2Json() {
        User user=new User();
        user.setId(1);
        user.setUserName("liuyang");
        user.setPassword("123");
        user.setBrief("好看");
        Set<String> strings=new HashSet<>();
        strings.add("test2");
        strings.add("teat3");
        strings.add("teat4");
        user.setUserNames(strings);
        String str =CommUtils.object2Json(user);
        System.out.println(str);
    }

    @Test
    public void json2Object() {
        String jsonStr="{\"id\":1,\"userName\":\"liuyang\",\"password\":\"123\",\"brief\":\"好看\",\"userNames\":[\"test2\",\"teat4\",\"teat3\"]}";
        User user= (User) CommUtils.json2Object(jsonStr,User.class);
        System.out.println(user.getBrief());
    }
}