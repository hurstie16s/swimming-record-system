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
    stroke TEXT,
    distance INTEGER,
    PRIMARY KEY (stroke, distance)
);
CREATE TABLE MeetInfo
(
    meet TEXT,
    dateRep TEXT,
    location TEXT,
    longCourse INTEGER,
    PRIMARY KEY (meet),
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
    meet INTEGER,
    stroke TEXT,
    distance INTEGER,
    raceTime REAL,
    PRIMARY KEY (username, meet, stroke, distance),
    FOREIGN KEY (username) REFERENCES NameInfo(username),
    FOREIGN KEY (meet) REFERENCES MeetInfo(meet),
    FOREIGN KEY (stroke) REFERENCES RaceEventInfo(stroke),
    FOREIGN KEY (distance) REFERENCES RaceEventInfo(distance)
);