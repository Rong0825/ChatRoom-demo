package com.java.char_room.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommUtils {
    //创建了gson对象,创建者模式
    private static final Gson GSON =new GsonBuilder().create();
    /*加载配置文件
     * filename:要加载的配置文件*/
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        //获取该类的类加载器.getResourceAsStream方法,加载指定的文件变为输入流
        InputStream in = CommUtils.class.getClassLoader().getResourceAsStream(fileName);//加载指定的文件变成资源
        try {
            properties.load(in);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
        return properties;
    }
    /*将任意对象序列化为json字符串*/
    public static String object2Json(Object obj)
    {
        return GSON.toJson(obj);
    }
    /*将任意字符串反序列化为对象
    * jsonStr:json字符串
    * objClass:反序列化的类反射对象 */
    public static Object json2Object (String jsonStr,Class objClass)
    {
        return GSON.fromJson(jsonStr,objClass);
    }

}