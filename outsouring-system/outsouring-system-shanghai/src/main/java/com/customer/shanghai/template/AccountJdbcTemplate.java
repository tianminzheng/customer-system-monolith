package com.customer.shanghai.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountJdbcTemplate extends AbstractJdbcTemplate {

	@Override
	protected Object handleResultSet(ResultSet rs) throws SQLException {

		List<Account> accounts = new ArrayList<Account>();
		while (rs.next()) {
			Account account = new Account(rs.getLong("id"), rs.getString("account_number"), rs.getString("account_name"));
			accounts.add(account);
		}
		return accounts;
	}
}

