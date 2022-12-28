package com.customer.shanghai.repository.impl;

import com.customer.shanghai.entity.ShanghaiCustomerStaff;
import com.customer.shanghai.repository.ShanghaiCustomerStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShanghaiCustomerStaffRepositoryImpl implements ShanghaiCustomerStaffRepository {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert staffInserter;

    @Autowired
    public ShanghaiCustomerStaffRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.staffInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("`shanghai_customer_staff` ").usingGeneratedKeyColumns("id");
    }

    @Override
    public List<ShanghaiCustomerStaff> findCustomerStaff() {

        return jdbcTemplate.query("select * from `shanghai_customer_staff`", this::mapRowToStaff);
    }

    private ShanghaiCustomerStaff mapRowToStaff(ResultSet resultSet, int i) throws SQLException{
        ShanghaiCustomerStaff staff = new ShanghaiCustomerStaff();
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
    public List<ShanghaiCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime) {
        return jdbcTemplate.query("select * from `shanghai_customer_staff` where updated_at > ?", this::mapRowToStaff,
                new java.sql.Timestamp(updatedTime));
    }

    @Override
    public Long createCustomerStaff(ShanghaiCustomerStaff customerStaff) {
//        PreparedStatementCreator psc = new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement("insert into `shanghai_customer_staff` (nickname, gender) values (?, ?)",
//                        Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1,customerStaff.getNickname());
//                ps.setString(2,customerStaff.getGender());
//
//                return ps;
//            }
//        };
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(psc, keyHolder);
//
//        return keyHolder.getKey().longValue();

        Map<String, Object> values = new HashMap<>();
        values.put("nickname", customerStaff.getNickname());
        values.put("gender", customerStaff.getGender());
        values.put("is_deleted", 0);
        values.put("created_at", new Date());
        values.put("updated_at", new Date());

        return staffInserter.executeAndReturnKey(values).longValue();
    }

    @Override
    public Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff) {
        return null;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {
        return null;
    }
}
