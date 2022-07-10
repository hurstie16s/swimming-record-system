SELECT
    RaceInfo.meet, RaceInfo.raceTime,
    MeetInfo.dateRep, MeetInfo.location, MeetInfo.longCourse,
    DateInfo.year
FROM RaceInfo
         LEFT JOIN MeetInfo
                   ON RaceInfo.meet = MeetInfo.meet
         LEFT JOIN DateInfo
                   ON MeetInfo.dateRep = DateInfo.dateRep
WHERE
        RaceInfo.username = ? AND
        RaceInfo.stroke = ? AND
        RaceInfo.distance = ? AND
        DateInfo.dateRep = ?
ORDER BY RaceInfo.raceTime
LIMIT 1;