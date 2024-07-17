CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    tc_number VARCHAR(20) UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);