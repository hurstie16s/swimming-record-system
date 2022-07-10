package com.hurst.swimTimes;

import com.hurst.sql.SqlCommandRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SwimTimeManagement {

    private static final Logger logger = LogManager.getLogger(SwimTimeManagement.class);

    private static final String[] strokes = {"FrontCrawl", "BackCrawl", "Breaststroke", "Butterfly", "Individual Medley"};

    public static SwimTimeRecord getLifetimeBest(String username, String stroke, int distance) {

        String meetRecord = null;
        int seasonRecord = 0;
        float timeRecord = 0;
        String dateRecord = null;
        boolean longCourseRecord = false;
        String locationRecord = null;

        String sql = "SELECT " +
                "RaceInfo.meet AS 'meet', RaceInfo.raceTime AS 'raceTime'," +
                "MeetInfo.dateRep AS 'dateRep', MeetInfo.location AS 'location', MeetInfo.longCourse AS 'longCourse'," +
                "DateInfo.year AS 'season'" +
                "FROM RaceInfo " +
                "LEFT JOIN MeetInfo " +
                "ON RaceInfo.meet = MeetInfo.meet " +
                "LEFT JOIN DateInfo " +
                "ON MeetInfo.dateRep = DateInfo.dateRep " +
                "WHERE " +
                "RaceInfo.username = ? AND " +
                "RaceInfo.stroke = ? AND " +
                "RaceInfo.distance = ? " +
                "ORDER BY RaceInfo.raceTime " +
                "LIMIT 1";

        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, stroke);
            statement.setInt(3, distance);
            ResultSet resultSet = statement.executeQuery();

            meetRecord = resultSet.getString("meet");
            seasonRecord = resultSet.getInt("season");
            timeRecord = resultSet.getFloat("raceTime");
            dateRecord = resultSet.getString("dateRep");
            longCourseRecord = (resultSet.getInt("longCourse") == 1);
            locationRecord = resultSet.getString("location");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return new SwimTimeRecord(stroke, distance, meetRecord, seasonRecord, timeRecord, dateRecord, longCourseRecord, locationRecord);
    }

    public static SwimTimeRecord getSeasonBest(String username, String stroke, int distance, int season) {
        String meetRecord = null;
        float timeRecord = 0;
        String dateRecord = null;
        boolean longCourseRecord = false;
        String locationRecord = null;

        String sql = "SELECT " +
                "RaceInfo.meet AS 'meet', RaceInfo.raceTime AS 'raceTime'," +
                "MeetInfo.dateRep AS 'dateRep', MeetInfo.location AS 'location', MeetInfo.longCourse AS 'longCourse' " +
                "FROM RaceInfo " +
                "LEFT JOIN MeetInfo " +
                "ON RaceInfo.meet = MeetInfo.meet " +
                "LEFT JOIN DateInfo " +
                "ON MeetInfo.dateRep = DateInfo.dateRep " +
                "WHERE " +
                "RaceInfo.username = ? AND " +
                "RaceInfo.stroke = ? AND " +
                "RaceInfo.distance = ? AND " +
                "DateInfo.dateRep = ? " +
                "ORDER BY RaceInfo.raceTime " +
                "LIMIT 1";

        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, stroke);
            statement.setInt(3, distance);
            ResultSet resultSet = statement.executeQuery();

            meetRecord = resultSet.getString("meet");
            timeRecord = resultSet.getFloat("raceTime");
            dateRecord = resultSet.getString("dateRep");
            longCourseRecord = (resultSet.getInt("longCourse") == 1);
            locationRecord = resultSet.getString("location");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return new SwimTimeRecord(stroke, distance, meetRecord, season, timeRecord, dateRecord, longCourseRecord, locationRecord);
    }

    public static SwimTimeRecord[] getAllStrokeLifetimeBest(String username, int distance) {
        SwimTimeRecord[] records = new SwimTimeRecord[5];
        for (int i = 0; i < 5; i++) {
            records[i] = getLifetimeBest(username, strokes[i], distance);
        }
        return records;
    }

    public static SwimTimeRecord[] getAllStrokeSeasonBest(String username, int distance, int season) {
        SwimTimeRecord[] records = new SwimTimeRecord[5];
        for (int i = 0; i < 5; i++) {
            records[i] = getSeasonBest(username, strokes[i], distance, season);
        }
        return records;
    }

    public static SwimTimeRecord[] getAllDistanceLifetimeBest(String username, String stroke) {
        ArrayList<Integer> distances = getDistances(stroke);
        SwimTimeRecord[] records = new SwimTimeRecord[distances.size()];
        for (int i = 0; i < distances.size(); i++) {
            records[i] = getLifetimeBest(username, stroke, distances.get(i));
        }
        return records;
    }

    public static SwimTimeRecord[] getAllDistanceSeasonBest(String username, String stroke, int season) {
        ArrayList<Integer> distances = getDistances(stroke);
        SwimTimeRecord[] records = new SwimTimeRecord[distances.size()];
        for (int i = 0; i < distances.size(); i++) {
            records[i] = getSeasonBest(username, stroke, distances.get(i), season);
        }
        return records;
    }

    private static ArrayList<Integer> getDistances(String stroke) {
        ArrayList<Integer> distances = new ArrayList<>();

        String sql = "SELECT distance FROM RaceEventInfo WHERE stroke = ?";
        try {
            PreparedStatement statement = SqlCommandRunner.databaseConnection.prepareStatement(sql);
            statement.setString(1, stroke);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                distances.add(resultSet.getInt("distance"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return distances;
    }

    public static SwimTimeRecord[] getAllLifetimeBest(String username) {
        return null;
    }

    public static SwimTimeRecord[] getAllSeasonBest(String username, int season) {
        return null;
    }

    public static void addMeet(String meet, String location, boolean longCourse, int day, int month, int year) {
    }

    public static void addRace(String stroke, int distance) {
    }

    public static void addTime(String username, String meet, String stroke, int distance, float time) {
    }
}
