package com.hurst.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * The type Sql command runner.
 */
public abstract class SqlCommandRunner {
    /**
     * The constant databaseConnection.
     */
    public static Connection databaseConnection;
    private static final Logger logger = LogManager.getLogger(SqlCommandRunner.class);

    /**
     * Initialise sql connection.
     *
     * @param url the url
     * @throws SQLException the sql exception
     */
    public static void initialiseSqlConnection(String url) throws SQLException {
        logger.info("Attempting to connect to Database " + url);
        databaseConnection = DriverManager.getConnection(url);
        logger.info("Attempting to create script runner for database connection "+databaseConnection.toString());
    }
}