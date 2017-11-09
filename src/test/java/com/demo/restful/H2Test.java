package com.demo.restful;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class H2Test {
	
	private EmbeddedDatabase db;
	
	private JdbcTemplate jdbc;
	
	@Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("db/sql/create-db.sql")
    		.addScript("db/sql/insert-data.sql")
    		.build();
    	
    	jdbc = new JdbcTemplate(db);
    }


	@Test
	public void testH2() {
		
		List res = jdbc.queryForList("select * from users");
		
		System.out.println(res.get(0));
	}
}
