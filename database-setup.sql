-- =============================================
-- Database Setup Script for Senimar E-Commerce
-- =============================================

-- Create database if it doesn't exist
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'Senimar')
BEGIN
    CREATE DATABASE Senimar;
    PRINT 'Database Senimar created successfully.';
END
ELSE
BEGIN
    PRINT 'Database Senimar already exists.';
END
GO

USE Senimar;
GO

-- =============================================
-- Insert Sample Data
-- =============================================

-- Insert Categories
IF NOT EXISTS (SELECT * FROM CATEGORY WHERE name = 'Electronics')
BEGIN
    INSERT INTO CATEGORY (name) VALUES 
    ('Electronics'),
    ('Clothing'),
    ('Books'),
    ('Home & Garden'),
    ('Sports & Outdoors');
    PRINT 'Sample categories inserted.';
END
ELSE
BEGIN
    PRINT 'Categories already exist.';
END
GO

-- Insert Sample Accounts/Customers
IF NOT EXISTS (SELECT * FROM ACCOUNT WHERE username = 'admin')
BEGIN
    INSERT INTO ACCOUNT (username, email, password, role, address) VALUES
    ('admin', 'admin@senimar.com', 'admin123', 'ADMIN', '123 Admin Street, City'),
    ('john_doe', 'john@example.com', 'password123', 'CUSTOMER', '456 Customer Ave, Town'),
    ('jane_smith', 'jane@example.com', 'password123', 'CUSTOMER', '789 Account Road, Village'),
    ('bob_wilson', 'bob@example.com', 'password123', 'CUSTOMER', '321 Buyer Lane, City'),
    ('alice_brown', 'alice@example.com', 'password123', 'CUSTOMER', NULL);
    PRINT 'Sample accounts inserted.';
END
ELSE
BEGIN
    PRINT 'Accounts already exist.';
END
GO

-- Insert Sample Products
-- Note: You need to get the actual category IDs first
DECLARE @ElectronicsId BIGINT, @ClothingId BIGINT, @BooksId BIGINT, @HomeId BIGINT, @SportsId BIGINT;

SELECT @ElectronicsId = id FROM CATEGORY WHERE name = 'Electronics';
SELECT @ClothingId = id FROM CATEGORY WHERE name = 'Clothing';
SELECT @BooksId = id FROM CATEGORY WHERE name = 'Books';
SELECT @HomeId = id FROM CATEGORY WHERE name = 'Home & Garden';
SELECT @SportsId = id FROM CATEGORY WHERE name = 'Sports & Outdoors';

IF NOT EXISTS (SELECT * FROM PRODUCT WHERE name = 'Laptop')
BEGIN
    INSERT INTO PRODUCT (name, description, price, quantity, weight, image_url, category_id) VALUES
    -- Electronics
    ('Laptop', 'High-performance laptop with 16GB RAM', 1200, 10, 2000, 'Product Images/laptop.jpg', @ElectronicsId),
    ('Smartphone', 'Latest smartphone with 5G support', 800, 25, 200, 'Product Images/phone.jpg', @ElectronicsId),
    ('Wireless Headphones', 'Noise-cancelling wireless headphones', 150, 50, 300, 'Product Images/headphones.jpg', @ElectronicsId),
    ('Smart Watch', 'Fitness tracking smart watch', 250, 30, 100, 'Product Images/watch.jpg', @ElectronicsId),
    
    -- Clothing
    ('T-Shirt', 'Cotton t-shirt in various colors', 25, 100, 200, 'Product Images/tshirt.jpg', @ClothingId),
    ('Jeans', 'Classic blue denim jeans', 60, 75, 500, 'Product Images/jeans.jpg', @ClothingId),
    ('Sneakers', 'Comfortable running sneakers', 80, 40, 400, 'Product Images/sneakers.jpg', @ClothingId),
    
    -- Books
    ('Programming Book', 'Learn Java programming from scratch', 45, 60, 800, 'Product Images/book.jpg', @BooksId),
    ('Novel', 'Bestselling fiction novel', 20, 80, 400, 'Product Images/novel.jpg', @BooksId),
    
    -- Home & Garden
    ('Coffee Maker', 'Automatic coffee maker with timer', 90, 20, 1500, 'Product Images/coffee.jpg', @HomeId),
    ('Plant Pot', 'Decorative ceramic plant pot', 15, 100, 600, 'Product Images/pot.jpg', @HomeId),
    
    -- Sports & Outdoors
    ('Yoga Mat', 'Non-slip yoga mat with carrying strap', 35, 50, 1000, 'Product Images/yoga.jpg', @SportsId),
    ('Dumbbell Set', '20kg adjustable dumbbell set', 120, 15, 20000, 'Product Images/dumbbell.jpg', @SportsId);
    
    PRINT 'Sample products inserted.';
END
ELSE
BEGIN
    PRINT 'Products already exist.';
END
GO

-- =============================================
-- Verify Data
-- =============================================

PRINT '=== Database Setup Complete ===';
PRINT '';
PRINT 'Categories Count: ' + CAST((SELECT COUNT(*) FROM CATEGORY) AS VARCHAR);
PRINT 'Accounts Count: ' + CAST((SELECT COUNT(*) FROM ACCOUNT) AS VARCHAR);
PRINT 'Products Count: ' + CAST((SELECT COUNT(*) FROM PRODUCT) AS VARCHAR);
PRINT '';
PRINT 'Sample Admin Login:';
PRINT 'Username: admin';
PRINT 'Password: admin123';
PRINT '';
PRINT 'Sample Customer Login:';
PRINT 'Username: john_doe';
PRINT 'Password: password123';
GO

-- =============================================
-- Useful Queries for Testing
-- =============================================

-- View all categories
-- SELECT * FROM CATEGORY;

-- View all accounts
-- SELECT id, username, email, role, address FROM ACCOUNT;

-- View all products with category names
-- SELECT p.id, p.name, p.description, p.price, p.quantity, p.weight, c.name as category_name
-- FROM PRODUCT p
-- INNER JOIN CATEGORY c ON p.category_id = c.id;

