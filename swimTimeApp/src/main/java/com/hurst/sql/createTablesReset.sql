DROP TABLE IF EXISTS RaceInfo;
DROP TABLE IF EXISTS NameInfo;
DROP TABLE IF EXISTS MeetInfo;
DROP TABLE IF EXISTS RaceEventInfo;
DROP TABLE IF EXISTS DateInfo;

CREATE TABLE DateInfo
(
    dateRep TEXT,
    day INTEGER,
    month INTEGER,
    year INTEGER
);

CREATE TABLE RaceEventInfo
(
    raceEventId INTEGER AUTOINCREMENT,
    stroke TEXT,
    distance INTEGER,
    PRIMARY KEY (raceEventId)
);

CREATE TABLE MeetInfo
(
    meetId INTEGER AUTOINCREMENT,
    dateRep TEXT,
    meet TEXT,
    location TEXT,
    longCourse INTEGER,
    PRIMARY KEY (meetId),
    FOREIGN KEY (dateRep) REFERENCES DateInfo(dateRep)
);

CREATE TABLE NameInfo
(
    username TEXT,
    firstName TEXT,
    surname TEXT,
    PRIMARY KEY (username)
);

CREATE TABLE RaceInfo
(
    username TEXT,
    meetId INTEGER,
    raceEventId INTEGER,
    raceTime REAL,
    PRIMARY KEY (username, meetId, raceEventId),
    FOREIGN KEY (username) REFERENCES NameInfo(username),
    FOREIGN KEY (meetId) REFERENCES MeetInfo(meetId),
    FOREIGN KEY (raceEventId) REFERENCES RaceEventInfo(raceEventId)
);