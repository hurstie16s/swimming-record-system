package com.hurst.sql;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SqlCommandRunnerTest {

    @DisplayName("Test exception on Database Connection Failure")
    @Test
    void initialiseSqlConnectionTestToFail() {
        assertThrows(SQLException.class, () -> SqlCommandRunner.initialiseSqlConnection("wrongUrl"));
    }

    @DisplayName("Test Database Connection Success")
    @Test
    void initialiseSqlConnectionTestToPass() {
        try {
            SqlCommandRunner.initialiseSqlConnection("jdbc:sqlite:identifier.sqlite");
            assertFalse(SqlCommandRunner.databaseConnection.isClosed());
        } catch (SQLException e) {
            fail();
        }
    }

    @AfterAll
    static void finish() {
        try {
            if(!SqlCommandRunner.databaseConnection.isClosed()) SqlCommandRunner.databaseConnection.close();
        } catch (SQLException ignored) {}
    }
}