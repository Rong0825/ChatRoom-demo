package com.java.char_room;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.java.char_room.util.CommUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class JDBCTest {
    private static DruidDataSource dataSource;
    //静态代码块,在类加载的时候被执行,且加载一次,静态资源可以在静态代码块中初始化
    static
    {
        Properties props=CommUtils.loadProperties("datasource.properties");
        //druid数据源
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    //单元测试--查询
    public void testQuery()
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection= (Connection) dataSource.getPooledConnection();//和数据池的连接
            String  sql="SELECT *FROM user";
            statement=connection.prepareStatement(sql);
            resultSet= statement.executeQuery();
            while(resultSet.next())
            {
                int id=resultSet.getInt("id");
                String userName=resultSet.getString("userName");
                String password=resultSet.getString("password");
                String brief=resultSet.getString("brief");
                System.out.println("id为"+id+"用户名:"+userName+"密码为:"+password+"简介:"+brief);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeResources(connection,statement,resultSet);
        }
    }
    //关闭资源的方法--更新删除和插入的关闭资源方法
    public  void closeResources(Connection connection, Statement statement)
    {
        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if( statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //select 操作多一个result
    public  void closeResources(Connection connection, Statement statement,ResultSet resultSet)
    {
        closeResources(connection,statement);
        if (resultSet !=null)
        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
//测试--插入
    public void testInsert()
    {
        Connection connection=null;
        PreparedStatement statment=null;

        try {
            connection=(Connection)dataSource.getPooledConnection();
            String password=DigestUtils.md5Hex("123");
            //?占位
            String sql="INSERT INTO user (username , password, brief) "+"VALUES(?,?,?)";
            statment =connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statment.setString(1,"美女");
            statment.setString(2,password);
            statment.setString(3,"超级无敌可爱的人");
            int rows=statment.executeUpdate();
            Assert.assertEquals(1,rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            closeResources(connection,statment);
        }
    }


}
