CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (content_id) REFERENCES content(id)
);
