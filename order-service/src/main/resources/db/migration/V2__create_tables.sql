-- ===================================
-- 1. CUSTOMERS
-- ===================================
CREATE TABLE IF NOT EXISTS `customers` (
                                           `id` INT AUTO_INCREMENT PRIMARY KEY,
                                           `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(150) NOT NULL,
    `phone` VARCHAR(50) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 2. DISCOUNT_CODES
--  (Created early so it’s available for order_discounts references)
-- ===================================
CREATE TABLE IF NOT EXISTS `discount_codes` (
                                                `id` INT AUTO_INCREMENT PRIMARY KEY,
                                                `code` VARCHAR(100) NOT NULL,
    `discount_type` VARCHAR(50) NOT NULL,  -- e.g., 'PERCENTAGE' or 'FIXED'
    `amount` DECIMAL(10, 2) NOT NULL,
    `start_date` DATETIME NULL,
    `end_date` DATETIME NULL,
    `usage_limit` INT DEFAULT 0,          -- 0 means unlimited usage
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `uc_discount_code` UNIQUE (`code`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 3. ORDERS
-- ===================================
CREATE TABLE IF NOT EXISTS `orders` (
                                        `id` INT AUTO_INCREMENT PRIMARY KEY,
                                        `customer_id` INT NOT NULL,
                                        `order_date` DATETIME NOT NULL,
                                        `status` VARCHAR(50) NOT NULL,        -- e.g., 'PENDING', 'SHIPPED'
    `total_amount` DECIMAL(10, 2) NOT NULL,
    `currency` VARCHAR(10) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_orders_customer` FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`)
                                                             ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 4. ORDER_ITEMS
-- ===================================
CREATE TABLE IF NOT EXISTS `order_items` (
                                             `id` INT AUTO_INCREMENT PRIMARY KEY,
                                             `order_id` INT NOT NULL,
                                             `product_id` INT NOT NULL,               -- typically references a Product Service ID
                                             `quantity` INT NOT NULL,
                                             `unit_price` DECIMAL(10, 2) NOT NULL,
    `total_price` DECIMAL(10, 2) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
                                                             ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 5. PAYMENTS
-- ===================================
CREATE TABLE IF NOT EXISTS `payments` (
                                          `id` INT AUTO_INCREMENT PRIMARY KEY,
                                          `order_id` INT NOT NULL,
                                          `payment_method` VARCHAR(50) NOT NULL,
    `payment_status` VARCHAR(50) NOT NULL,   -- e.g., 'PENDING', 'COMPLETED', 'FAILED'
    `amount_paid` DECIMAL(10, 2) NOT NULL,
    `transaction_id` VARCHAR(100) NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_payments_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
                                                             ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 6. SHIPPING_DETAILS
-- ===================================
CREATE TABLE IF NOT EXISTS `shipping_details` (
                                                  `id` INT AUTO_INCREMENT PRIMARY KEY,
                                                  `order_id` INT NOT NULL,
                                                  `address_line1` VARCHAR(255) NOT NULL,
    `address_line2` VARCHAR(255) DEFAULT '',
    `city` VARCHAR(100) NOT NULL,
    `state` VARCHAR(100) NOT NULL,
    `postal_code` VARCHAR(20) NOT NULL,
    `country` VARCHAR(100) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_shipping_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
                                                             ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 7. ORDER_STATUS_HISTORY
-- ===================================
CREATE TABLE IF NOT EXISTS `order_status_history` (
                                                      `id` INT AUTO_INCREMENT PRIMARY KEY,
                                                      `order_id` INT NOT NULL,
                                                      `old_status` VARCHAR(50) NOT NULL,
    `new_status` VARCHAR(50) NOT NULL,
    `changed_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `changed_by` VARCHAR(100) NULL,         -- could store username or userId
    CONSTRAINT `fk_order_status_history_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 8. INVOICES
-- ===================================
CREATE TABLE IF NOT EXISTS `invoices` (
                                          `id` INT AUTO_INCREMENT PRIMARY KEY,
                                          `order_id` INT NOT NULL,
                                          `invoice_number` VARCHAR(100) NOT NULL,
    `invoice_date` DATETIME NOT NULL,
    `due_date` DATETIME NULL,
    `total_amount` DECIMAL(10, 2) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_invoices_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
                                                             ON DELETE CASCADE,
    CONSTRAINT `uc_invoice_number` UNIQUE (`invoice_number`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 9. REFUNDS
-- ===================================
CREATE TABLE IF NOT EXISTS `refunds` (
                                         `id` INT AUTO_INCREMENT PRIMARY KEY,
                                         `order_id` INT NOT NULL,
                                         `refund_amount` DECIMAL(10, 2) NOT NULL,
    `refund_reason` VARCHAR(255) NOT NULL,
    `processed_by` VARCHAR(100) NULL,
    `processed_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_refunds_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================================
-- 10. ORDER_DISCOUNTS
--  (Joins an order with a discount code; references both orders and discount_codes)
-- ===================================
CREATE TABLE IF NOT EXISTS `order_discounts` (
                                                 `id` INT AUTO_INCREMENT PRIMARY KEY,
                                                 `order_id` INT NOT NULL,
                                                 `discount_id` INT NOT NULL,
                                                 `discount_amount` DECIMAL(10, 2) NOT NULL,
    `applied_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_order_discounts_order` FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_order_discounts_discount` FOREIGN KEY (`discount_id`)
    REFERENCES `discount_codes` (`id`)
    ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
