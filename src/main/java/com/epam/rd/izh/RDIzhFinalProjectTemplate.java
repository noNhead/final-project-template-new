package com.epam.rd.izh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>Класс с main методом.
 * <p>Аннотация @SpringBootApplication является мета-аннотацией, т.е. по сути, является алиасом для нескольких аннотаций:
 * <br>- {@link SpringBootConfiguration SpringBootConfiguration}
 * <br>- {@link EnableAutoConfiguration EnableAutoConfiguration}
 * <br>- {@link ComponentScan ComponentScan}
 * <p>Т.е. наличие @SpringBootApplication включает сканирование компонентов, автоконфигурацию и показывает разным компонентам Spring
 * (например, интеграционным тестам), что это Spring Boot приложение.
 */
@SpringBootApplication
public class RDIzhFinalProjectTemplate {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String userNameDatabase = "root";
		String passwordDatabase = "1";
		String connectionUrl = "jdbc:mysql://localhost:3306/mysql";
		Class.forName("com.mysql.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection(connectionUrl, userNameDatabase, passwordDatabase)){
			System.out.println("connected");
		}
		SpringApplication.run(RDIzhFinalProjectTemplate.class, args);
	}
}
