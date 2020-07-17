package com.epam.rd.izh.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConn {
    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        String userNameDatabase = "root";
        String passwordDatabase = "1";
        String connectionUrl = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, userNameDatabase, passwordDatabase)) {
            System.out.println("connected");
            return connection.createStatement();
        }
    }
}
