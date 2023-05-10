CREATE TABLE IF NOT EXISTS Note (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
        CHECK(LENGTH(title)>5),
    content VARCHAR(10000) NOT NULL
        CHECK(LENGTH(content)>5),
    accesstype VARCHAR(10),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id)
    );