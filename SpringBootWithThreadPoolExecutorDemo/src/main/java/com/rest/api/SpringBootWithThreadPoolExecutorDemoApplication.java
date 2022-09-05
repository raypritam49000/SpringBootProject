package com.rest.api;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class SpringBootWithThreadPoolExecutorDemoApplication implements CommandLineRunner {

	@Autowired
	private ThreadPoolExecutor threadPoolExecutor;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithThreadPoolExecutorDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		threadPoolExecutor.execute(() -> {
			 try {
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hello");
		});

	}

}
