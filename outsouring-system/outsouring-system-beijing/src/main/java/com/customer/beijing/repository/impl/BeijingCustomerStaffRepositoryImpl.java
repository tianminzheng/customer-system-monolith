package com.customer.beijing.repository.impl;

import com.customer.beijing.entity.BeijingCustomerStaff;
import com.customer.beijing.repository.BeijingCustomerStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BeijingCustomerStaffRepositoryImpl implements BeijingCustomerStaffRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<BeijingCustomerStaff> findCustomerStaff() {

        List<BeijingCustomerStaff> staffs = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from `beijing_customer_staff`");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffs.add(convertStaff(resultSet));
            }
            return staffs;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return staffs;
    }

    @Override
    public List<BeijingCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime) {

        List<BeijingCustomerStaff> staffs = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from `beijing_customer_staff` where updated_at > ?");
            statement.setTimestamp(1, new java.sql.Timestamp(updatedTime));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffs.add(convertStaff(resultSet));
            }
            return staffs;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return staffs;
    }

    private BeijingCustomerStaff convertStaff(ResultSet resultSet) throws SQLException {
        BeijingCustomerStaff staff = new BeijingCustomerStaff();
        staff.setId(resultSet.getLong("id"));
        staff.setNickname(resultSet.getString("nickname"));
        staff.setGender(resultSet.getString("gender"));
        staff.setPhone(resultSet.getString("phone"));
        staff.setAvatar(resultSet.getString("avatar"));
        staff.setGoodAt(resultSet.getString("good_at"));
        staff.setRemark(resultSet.getString("remark"));

        return staff;
    }

    @Override
    public Long createCustomerStaff(BeijingCustomerStaff customerStaff) {
        //TODO
        return null;
    }

    @Override
    public Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff) {
        //TODO
        return null;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {
        //TODO
        return null;
    }
}
