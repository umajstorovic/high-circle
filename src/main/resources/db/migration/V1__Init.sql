-- Create the movies table
CREATE TABLE movies (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        release_date DATE,
                        poster_url VARCHAR(500),
                        overview TEXT,
                        genres TEXT[],
                        rating DOUBLE PRECISION,
                        runtime INTEGER,
                        language VARCHAR(50)
);