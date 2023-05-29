package com.customer.shanghai.repository.impl;

import com.customer.shanghai.entity.ShanghaiCustomerStaff;
import com.customer.shanghai.repository.ShanghaiCustomerStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShanghaiCustomerStaffRepositoryImpl implements ShanghaiCustomerStaffRepository {
    //定义通用结果集映射
    private static final RowMapper<ShanghaiCustomerStaff> ROW_MAPPER = (rs, rowNum) -> {
        ShanghaiCustomerStaff staff = new ShanghaiCustomerStaff();
        staff.setId(rs.getLong("id"));
        staff.setNickname(rs.getString("nickname"));
        staff.setGender(rs.getString("gender"));
        staff.setPhone(rs.getString("phone"));
        staff.setAvatar(rs.getString("avatar"));
        staff.setGoodAt(rs.getString("good_at"));
        staff.setRemark(rs.getString("remark"));
        staff.setCreatedAt(rs.getDate("created_at"));
        return staff;
    };


    @Resource
    private JdbcTemplate jdbcTemplate;  //注入Spring JdbcTemplate

    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        //构建SimpleJdbcInsert,用于简化插入操作
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("`shanghai_customer_staff` ")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<ShanghaiCustomerStaff> findCustomerStaff() {
        return jdbcTemplate.query("select * from `shanghai_customer_staff`", ROW_MAPPER);
    }

    @Override
    public List<ShanghaiCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime) {
        return jdbcTemplate.query("select * from `shanghai_customer_staff` where updated_at > ?", ROW_MAPPER,
                new java.sql.Timestamp(updatedTime));
    }

    @Override
    public Long createCustomerStaff(ShanghaiCustomerStaff customerStaff) {
        Map<String, Object> values = new HashMap<>();
        values.put("nickname", customerStaff.getNickname());
        values.put("avatar", customerStaff.getAvatar());
        values.put("phone", customerStaff.getPhone());
        values.put("gender", customerStaff.getGender());
        values.put("good_at", customerStaff.getGoodAt());
        values.put("remark", customerStaff.getRemark());
        values.put("is_deleted", 0);
        values.put("created_at", new Date());
        values.put("updated_at", new Date());
        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }

    @Override
    public Boolean updateCustomerStaff(ShanghaiCustomerStaff customerStaff) {
        String sql = " update `shanghai_customer_staff` " +
                " set nickname = ?, avatar = ?, phone = ?, gender = ?, good_at = ?, remark = ? " +
                " where id = ? ";
        int row = jdbcTemplate.update(sql, ps -> {
            ps.setString(1, customerStaff.getNickname());
            ps.setString(2, customerStaff.getAvatar());
            ps.setString(3, customerStaff.getPhone());
            ps.setString(4, customerStaff.getGender());
            ps.setString(5, customerStaff.getGoodAt());
            ps.setString(6, customerStaff.getRemark());
            ps.setLong(7, customerStaff.getId());
        });

        return (row == 1);
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {
        String sql = " update `shanghai_customer_staff` set is_deleted = 1 where id = ? ";
        int row = jdbcTemplate.update(sql, ps -> ps.setLong(1, id));
        return (row == 1);
    }
}
