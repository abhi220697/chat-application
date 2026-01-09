-- Flyway Migration: Insert sample users

INSERT INTO users (
    id, name, age, sex, phone_no, email, password_hash, created_at, updated_at, status
) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Abhishek Raj', 28, 'Male', '9876543210', 'abhishek.raj@example.com', 'hashed-password-1', NOW(), NOW(), 'ACTIVE'),
    ('22222222-2222-2222-2222-222222222222', 'John Doe', 30, 'Male', '9998887776', 'john.doe@example.com', 'hashed-password-2', NOW(), NOW(), 'ACTIVE'),
    ('33333333-3333-3333-3333-333333333333', 'Jane Smith', 26, 'Female', '9123456789', 'jane.smith@example.com', 'hashed-password-3', NOW(), NOW(), 'INACTIVE');
