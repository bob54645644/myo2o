package com.bob.demo.myo2o;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class Myo2oApplicationTests {
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		System.out.println("hello myo2o!");
		System.out.println(jdbcOperations.getClass().getSimpleName());
		System.out.println(jdbcTemplate.getDataSource());
	}

}
