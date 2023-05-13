CREATE TABLE IF NOT EXISTS Note (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
        CHECK(LENGTH(title)>5),
    content VARCHAR(10000) NOT NULL
        CHECK(LENGTH(content)>5),
    accesstype VARCHAR(10),
    userid BIGINT,
    FOREIGN KEY (userid) REFERENCES Users(id)
    );