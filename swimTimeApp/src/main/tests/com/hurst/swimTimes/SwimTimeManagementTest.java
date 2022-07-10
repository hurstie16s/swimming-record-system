package com.hurst.swimTimes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SwimTimeManagementTest {

    public static Connection testDatabaseConnection;
    private static final Logger logger = LogManager.getLogger(SwimTimeManagementTest.class);

    private static final String sqlDate = "INSERT INTO DateInfo(dateRep, day, month, year) VALUES(?,?,?,?)";
    private static final String sqlRaceEventInfo = "INSERT INTO RaceEventInfo(stroke, distance) VALUES(?,?)";
    private static final String sqlMeetInfo = "INSERT INTO MeetInfo(meet, dateRep, location, longCourse) VALUES(?,?,?,?)";
    private static final String sqlNameInfo = "INSERT INTO NameInfo(username, firstName, surname) VALUES(?,?,?)";
    private static final String sqlRaceInfo = "INSERT INTO RaceInfo(username, meet, stroke, distance, raceTime) VALUES(?,?,?,?,?)";

    @BeforeAll
    static void start() {
        try {
            testDatabaseConnection = DriverManager.getConnection("jdbc:sqlite:testDatabase");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        insertTestData();
    }

    static void insertTestData() {
        dateInsert("2021-12-20", 20, 1, 2021);
        dateInsert("2021-01-20", 20, 1, 2021);
        dateInsert("2020-12-20", 20, 1, 2021);
        dateInsert("2020-01-20", 20, 1, 2021);

        raceEventInsert("FrontCrawl", 50);
        raceEventInsert("FrontCrawl", 100);
        raceEventInsert("FrontCrawl", 200);
        raceEventInsert("Butterfly", 50);
        raceEventInsert("Butterfly", 100);

        meetInfoInsert("2021 Derbyshire LongCourse Championship Ponds Forge Sheffield",
                "2021-12-20",
                "Ponds Forge - Sheffield",
                1);
        meetInfoInsert("2021 Derbyshire ShortCourse Championship Ponds Forge Sheffield",
                "2021-01-20",
                "Ponds Forge - Sheffield",
                0);
        meetInfoInsert("2020 Derbyshire LongCourse Championship Ponds Forge Sheffield",
                "2020-12-20",
                "Ponds Forge - Sheffield",
                1);
        meetInfoInsert("2020 Derbyshire ShortCourse Championship Ponds Forge Sheffield",
                "2020-01-20",
                "Ponds Forge - Sheffield",
                0);
    }

    static void dateInsert(String dateRep, int day, int month, int year) {
        try {
            PreparedStatement statement = testDatabaseConnection.prepareStatement(sqlDate);
            statement.setString(1, dateRep);
            statement.setInt(2, day);
            statement.setInt(3, month);
            statement.setInt(4, year);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void raceEventInsert(String stroke, int distance) {
        try {
            PreparedStatement statement = testDatabaseConnection.prepareStatement(sqlRaceEventInfo);
            statement.setString(1, stroke);
            statement.setInt(2, distance);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void meetInfoInsert(String meet, String dateRep, String location, int longCourse) {
        try {
            PreparedStatement statement = testDatabaseConnection.prepareStatement(sqlMeetInfo);
            statement.setString(1, meet);
            statement.setString(2, dateRep);
            statement.setString(3, location);
            statement.setInt(4, longCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addMeet() {
    }

    @Test
    void addRace() {
    }

    @Test
    void addTime() {
    }

    @Test
    void getLifetimeBest() {
    }

    @Test
    void getSeasonBest() {
    }

    @Test
    void getAllStrokeLifetimeBest() {
    }

    @Test
    void getAllStrokeSeasonBest() {
    }

    @Test
    void getAllDistanceLifetimeBest() {
    }

    @Test
    void getAllDistanceSeasonBest() {
    }

    @Test
    void getDistancesTest() {
    }

    @Test
    void getAllLifetimeBest() {
    }

    @Test
    void getAllSeasonBest() {
    }
}