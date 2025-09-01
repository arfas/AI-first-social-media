CREATE TABLE content (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    caption TEXT,
    created_at TIMESTAMP NOT NULL
);
