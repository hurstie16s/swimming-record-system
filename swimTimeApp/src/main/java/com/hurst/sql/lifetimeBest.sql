SELECT meet, raceTime FROM RaceInfo WHERE username = ? AND stroke = ? AND distance = ? ORDER BY raceTime LIMIT 1;
SELECT dateRep, location, longCourse FROM MeetInfo WHERE meet = ?;
SELECT day, month, year FROM DateInfo WHERE dateRep = ?;

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
    RaceInfo.distance = ?
ORDER BY RaceInfo.raceTime
LIMIT 1;