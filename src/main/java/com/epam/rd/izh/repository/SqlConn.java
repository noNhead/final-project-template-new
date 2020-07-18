package com.epam.rd.izh.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.epam.rd.izh.util.StringConstants.*;

public class SqlConn {
    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        try(Connection connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS)) {
            System.out.println("connected");
            return connection.createStatement();
        }
    }
}
