package com.hurst.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SqlCommandRunner {
    public static Connection databaseConnection;
    private static final Logger logger = LogManager.getLogger(SqlCommandRunner.class);

    public static void initialiseSqlConnection() {
        try {
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (SQLException e) {
            logger.error("Could not connect to SQLite database");
            System.exit(1);
        }
    }
}
