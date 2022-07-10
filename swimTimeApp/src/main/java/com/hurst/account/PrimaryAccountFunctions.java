package com.hurst.account;

import com.hurst.sql.SqlCommandRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Primary account functions.
 */
public abstract class PrimaryAccountFunctions {

    private static final Logger logger = LogManager.getLogger(PrimaryAccountFunctions.class);

    /**
     * Sign in boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public static boolean signIn(String username) {
        String sql = "SELECT username FROM NameInfo WHERE username = ?";
        String usernameCheck = null;
        ResultSet result = null;
        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement.setString(1, username);
            result = statement.executeQuery();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            usernameCheck = result.getString("username");
        } catch (SQLException ignored) {}

        return usernameCheck != null;
    }

    /**
     * Sign up string.
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @return the string
     */
    public static String signUp(String firstname, String lastname) {
        String username = firstname.split("")[0] + lastname;

        String sql = "SELECT COUNT(username) AS 'usernameCount' FROM NameInfo WHERE username LIKE ?";
        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement.setString(1, username + "%");
            ResultSet result = statement.executeQuery();

            int numberOfOccurrences = result.getInt("usernameCount");
            if (numberOfOccurrences != 0) {
                username += numberOfOccurrences;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        String sql2 = "INSERT INTO NameInfo(username,firstname,surname) VALUES(?,?,?)";
        try {

            PreparedStatement statement2 = SqlCommandRunner.databaseConnection.prepareStatement(sql2);
            statement2.setString(1, username);
            statement2.setString(2, firstname);
            statement2.setString(3, lastname);
            statement2.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return username;
    }
}