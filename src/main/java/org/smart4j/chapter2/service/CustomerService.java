package org.smart4j.chapter2.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropsUtil;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class CustomerService {
    private static final Logger LOGGER= LoggerFactory.getLogger(DatabaseHelper.class);
    public static final String DRIVER;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;

    static {
        Properties properties = PropsUtil.loadProps(("config.properties"));
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    //获取用户列表
    public List<Customer> getCustomerList() {
       // Connection connection = null;
       // Connection connection=DatabaseHelper.getConnection();
       // try {
            //List<Customer> customerList = new ArrayList<Customer>();
            String sql = "SELECT * FROM customer";
            return DatabaseHelper.quertEntityList(Customer.class,sql);
            //connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // connection=DatabaseHelper.getConnection();//(1)
           // PreparedStatement preparedStatement = connection.prepareStatement(sql);
           // ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Customer customer = new Customer();
//                customer.setId(resultSet.getLong("id"));
//                customer.setName((resultSet.getString("name")));
//                customer.setContact(resultSet.getString("contact"));
//                customer.setContact(resultSet.getString("telephone"));
//                customer.setEmail(resultSet.getString("emil"));
//                customer.setRemark(resultSet.getString("remark"));
//                customerList.add(customer);
//            }
//            return customerList;
//        } catch (SQLException e) {
//            LOGGER.error("execute sql failure", e);
      //  } finally {
       //     DatabaseHelper.closeConnection(connection);//,<2>
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    LOGGER.error("close connection failure", e);
//                }
//            }
      //  }
    }

    //获取客户
    public Customer getCustomer(long id) {
        return null;
    }

    //创建客户
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return false;
    }

    //更新客户
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return false;
    }

    public boolean deleteCustomer(long id) {
        return false;
    }
}
