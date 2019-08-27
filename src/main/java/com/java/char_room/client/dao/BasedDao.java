package com.java.char_room.client.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.java.char_room.util.CommUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*获取基础基础dao操作,获取数据源,连接,关闭资源等*/
public class BasedDao {
    //所有子类不直接使用,只需要连接
    private static DruidDataSource dataSource;
    //静态代码块,在类加载的时候被执行,且加载一次,静态资源可以在静态代码块中初始化
    //数据源的加载
    static {
        Properties properties=CommUtils.loadProperties("datasource.properties");
        try
        {
            //获取连接池的对象
            dataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        }catch(Exception e)
        {
            System.err.println("数据源加载失败");
            e.printStackTrace();
        }
    }
    //不直接使用数据源,获取连接,使用Protected有继承权限
    protected DruidPooledConnection getConnection() {

        try {
            return (DruidPooledConnection) dataSource.getPooledConnection();

        }catch(Exception e)
        {
            System.err.println("数据库连接获取失败");
            e.printStackTrace();
        }
        return null;
    }

    protected void  closeResources(Connection connection, Statement statement) {
        if (connection != null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //重载函数
    protected void  closeResources(Connection connection,
                                   Statement statement,
                                   ResultSet resultSet) {
        //复用
        closeResources(connection,statement);

        if(resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
