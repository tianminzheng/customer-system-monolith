package com.customer.shanghai.template;

import java.util.List;


public class AbstractJdbcTemplateTest {

	@SuppressWarnings("unchecked")
	public void test() {
	     AbstractJdbcTemplate jdbcTemplate = new AccountJdbcTemplate();  
	     List<Account> account = (List<Account>) jdbcTemplate.execute("select * from Account");
	}
}
