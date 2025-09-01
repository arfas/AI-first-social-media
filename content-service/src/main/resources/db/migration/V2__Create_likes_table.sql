CREATE TABLE likes (
    user_id BIGINT NOT NULL,
    content_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, content_id),
    FOREIGN KEY (content_id) REFERENCES content(id)
);
