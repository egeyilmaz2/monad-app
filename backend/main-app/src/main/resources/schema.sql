
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
                       id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       tc_number VARCHAR(255),
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);