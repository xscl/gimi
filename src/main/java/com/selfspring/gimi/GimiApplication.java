package com.selfspring.gimi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
@ComponentScan("com.selfspring.gimi")
@EnableScheduling
public class GimiApplication implements CommandLineRunner {
	@Resource
	DataSource dataSource;

//	@Test
//	public void contextLoads() throws SQLException {
//
//		System.out.println("数据源>>>>>>" + dataSource.getClass());
//		Connection connection = dataSource.getConnection();
//
//		System.out.println("连接>>>>>>>>>" + connection);
//		System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
//		connection.close();
//	}


	public static void main(String[] args) {
		SpringApplication.run(GimiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		showConnections();
	}

	private void showConnections() throws SQLException {
		log.info(dataSource.getConnection().toString());
	}
}
