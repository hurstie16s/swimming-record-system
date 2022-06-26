package com.hurst.account;

import com.hurst.sql.SqlCommandRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryAccountFunctionsTest {

    private static final Logger logger = LogManager.getLogger(PrimaryAccountFunctionsTest.class);

    @BeforeAll
    static void setUp() {
        try {
            SqlCommandRunner.initialiseSqlConnection("jdbc:sqlite:identifier.sqlite");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }

    @DisplayName("Check a user can sign in")
    @Test
    void signIn() {
        String sql = "INSERT INTO NameInfo(username,firstname,surname) VALUES(?,?,?)";

        try {

            PreparedStatement statement2 = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement2.setString(1, "SUp");
            statement2.setString(2, "Sign");
            statement2.setString(3, "Up");
            statement2.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        assert(PrimaryAccountFunctions.signIn("SUp"));
    }

    @DisplayName("Check a user can sign up and a second user can sign up with the same name")
    @Test
    void signUp() {
        PrimaryAccountFunctions.signUp("test", "Last");
        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement("SELECT COUNT(username) AS 'usernameCount' FROM NameInfo WHERE username = 'tLast'");
            ResultSet resultSet = statement.executeQuery();

            assertEquals(1, resultSet.getInt("usernameCount"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    @AfterAll
    static void tearDown() {
        try {
            SqlCommandRunner.databaseConnection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }
}