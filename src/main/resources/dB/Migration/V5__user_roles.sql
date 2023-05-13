CREATE TABLE IF NOT EXISTS user_roles (
    userid BIGINT,
    roleid BIGINT,
    PRIMARY KEY (userid, roleid),
    FOREIGN KEY (userid) REFERENCES users(id),
    FOREIGN KEY (roleid) REFERENCES roles(id)
    );