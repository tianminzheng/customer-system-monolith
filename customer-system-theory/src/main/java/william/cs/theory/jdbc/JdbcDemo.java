package william.cs.theory.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShenao
 * @date 2023/5/29 2:07 PM
 * Description 原生JDBC的使用方式
 */
public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        //1. 注册数据库驱动/创建数据源DataSource
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. 创建数据库连接Connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer_system?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "12345678");

        //3. 创建执行语句Statement
        String sql = " select * from `customer_group` ";
        PreparedStatement stmt = conn.prepareStatement(sql);

        //4. 执行SQL语句,获取结果集ResultSet
        ResultSet resultSet = stmt.executeQuery();

        //5. 解析ResultSet,获取业务对象
        List<CustomerGroup> customerGroups = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String groupName = resultSet.getString("group_name");
            String groupDescription = resultSet.getString("group_description");
            customerGroups.add(new CustomerGroup(id, groupName, groupDescription));
        }
        System.out.println("CustomerGroup: " + customerGroups);

        //6. 释放资源对象
        resultSet.close();
        stmt.close();
        conn.close();
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class CustomerGroup {
        private long id;
        private String groupName;
        private String groupDescription;
    }
}
