package com.hurst.swimTimes;

public record SwimTimeRecord(String stroke,
                             int distance,
                             String meet,
                             int season,
                             float time,
                             String date,
                             boolean longCourse,
                             String location) {}
