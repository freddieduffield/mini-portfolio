CREATE TABLE users (
                       user_id UUID PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE portfolios (
                            id UUID PRIMARY KEY,
                            user_id UUID
                                CONSTRAINT fk_user_id REFERENCES users,
                            name VARCHAR(100) NOT NULL,
                            total_value DECIMAL(15, 2) DEFAULT 0,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
