package org.smart4j.chapter2.helper;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

//数据库操作助手类
public  class DatabaseHelper {
    private static  final QueryRunner QUERY_RUNNER=new QueryRunner();

    private static final ThreadLocal<Connection> CONNECTION_HOLDER=new ThreadLocal<Connection>();


    private static final Logger LOGGER= LoggerFactory.getLogger(DatabaseHelper.class);
    private static final String DRIVER;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    static{
        Properties propertoes= PropsUtil.loadProps("confi.properties");
        DRIVER=propertoes.getProperty("jdbc.driver");
        URL=propertoes.getProperty("jdbc.url");
        USERNAME=propertoes.getProperty("jdbc.username");
        PASSWORD=propertoes.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("can not load jdbc driver",e);
        }
    }
    //获取数据库连接
    public static Connection getConnection(){
       // Connection connection=null;
        Connection connection=CONNECTION_HOLDER.get();//<1>
        if(connection==null){
            try {
                connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }catch (SQLException e){
                LOGGER.error("get connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(connection);//<2>
            }
        }
//        try {
//            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        }catch (SQLException e){
//            LOGGER.error("get connect failure",e);
//        }
        return connection;
    }//关闭数据库
    //public static void closeConnection(Connection connection){
    public static void closeConnection(){
        Connection connection=CONNECTION_HOLDER.get();//<1>

        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                LOGGER.error("close connection failure",e);
            }finally {
                CONNECTION_HOLDER.remove();//<3>
            }
        }
    }
//查询实体列表
    public static <T> List<T> quertEntityList(Class<T>entityClass, String sql, Object...params){
        List<T> entityList;
        Connection connection=getConnection();
        try {
            entityList=QUERY_RUNNER.query(connection,sql,new BeanListHandler<T>(entityClass),params);

        }catch (SQLException e){
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entityList;
    }

    //查询实体
    public static <T> T queryENtity(Class<T>entityClass,String sql,Object...params){
        T entity;
        try {
            Connection connection=getConnection();
            entity=QUERY_RUNNER.query(connection,sql,new BeanHandler<T>(entityClass),params);
        }catch (SQLException e){
            LOGGER.error("query entity failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entity;
    }

}
