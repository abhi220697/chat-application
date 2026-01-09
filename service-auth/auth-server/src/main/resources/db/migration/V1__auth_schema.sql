CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE auth_users (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                            username VARCHAR(50) NOT NULL UNIQUE,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            password VARCHAR(500) NOT NULL,

                            roles VARCHAR(50)[] NOT NULL,

                            enabled BOOLEAN NOT NULL DEFAULT TRUE,
                            account_locked BOOLEAN NOT NULL DEFAULT FALSE,

                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP
);

CREATE INDEX idx_auth_users_username ON auth_users(username);
CREATE INDEX idx_auth_users_email ON auth_users(email);
CREATE INDEX idx_auth_users_roles ON auth_users USING GIN (roles);


-- Seed user (password = admin123)
INSERT INTO auth_users (username, email, password, roles)
VALUES (
           'admin',
           'admin@local',
           '$2a$10$C6UzMDM.H6dfI/f/IKcEeO1iF0q1pGxkCqv9r8pV1Zz7zK5eQp6yS',
           ARRAY['ROLE_ADMIN']
       )
    ON CONFLICT DO NOTHING;

