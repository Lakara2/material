CREATE TABLE IF NOT EXISTS material (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT
);
