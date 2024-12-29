-- =======================================
-- 1. CUSTOMERS
-- =======================================
INSERT INTO `customers` (
    `id`, `first_name`, `last_name`, `email`, `phone`, `created_at`, `updated_at`
) VALUES
      (1, 'John', 'Doe', 'john@example.com', '1111111111', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
      (2, 'Jane', 'Smith', 'jane@example.com', '2222222222', '2024-01-02 11:00:00', '2024-01-02 11:00:00'),
      (3, 'Alice', 'Brown', 'alice@example.com', '3333333333', '2024-01-03 12:00:00', '2024-01-03 12:00:00'),
      (4, 'Bob', 'Johnson', 'bob@example.com', '4444444444', '2024-01-04 13:00:00', '2024-01-04 13:00:00'),
      (5, 'Eve', 'Davis', 'eve@example.com', '5555555555', '2024-01-05 14:00:00', '2024-01-05 14:00:00');

-- =======================================
-- 2. DISCOUNT_CODES
-- =======================================
INSERT INTO `discount_codes` (
    `id`, `code`, `discount_type`, `amount`, `start_date`, `end_date`, `usage_limit`, `created_at`, `updated_at`
) VALUES
      (1, 'DISCOUNT10', 'PERCENTAGE', 10.00, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 100, NOW(), NOW()),
      (2, 'DISCOUNT5',  'FIXED',      5.00,  '2024-01-15 00:00:00', '2024-03-31 23:59:59', 50,  NOW(), NOW()),
      (3, 'WELCOME15',  'PERCENTAGE', 15.00, '2024-02-01 00:00:00', '2024-05-01 23:59:59', 0,   NOW(), NOW()),
      (4, 'FREESHIP',   'FIXED',      0.00,  '2024-01-01 00:00:00', '2024-12-31 23:59:59', 999, NOW(), NOW()),
      (5, 'SPRINGSALE', 'PERCENTAGE', 20.00, '2024-03-01 00:00:00', '2024-03-31 23:59:59', 500, NOW(), NOW());

-- =======================================
-- 3. ORDERS
-- =======================================
INSERT INTO `orders` (
    `id`, `customer_id`, `order_date`, `status`, `total_amount`, `currency`, `created_at`, `updated_at`
) VALUES
      (1, 1, '2024-01-10 09:00:00', 'PENDING',    100.00, 'USD', NOW(), NOW()),
      (2, 2, '2024-01-11 10:30:00', 'PROCESSING', 200.00, 'USD', NOW(), NOW()),
      (3, 3, '2024-01-12 11:45:00', 'SHIPPED',    300.00, 'USD', NOW(), NOW()),
      (4, 4, '2024-01-13 12:15:00', 'DELIVERED',  400.00, 'USD', NOW(), NOW()),
      (5, 5, '2024-01-14 13:25:00', 'CANCELLED',  150.00, 'USD', NOW(), NOW());

-- =======================================
-- 4. ORDER_ITEMS
-- =======================================
INSERT INTO `order_items` (
    `id`, `order_id`, `product_id`, `quantity`, `unit_price`, `total_price`, `created_at`, `updated_at`
) VALUES
      (1, 1, 101, 2,  50.00, 100.00, NOW(), NOW()),
      (2, 2, 102, 4,  50.00, 200.00, NOW(), NOW()),
      (3, 3, 103, 3, 100.00, 300.00, NOW(), NOW()),
      (4, 4, 104, 2, 200.00, 400.00, NOW(), NOW()),
      (5, 5, 105, 5,  30.00, 150.00, NOW(), NOW());

-- =======================================
-- 5. PAYMENTS
-- =======================================
INSERT INTO `payments` (
    `id`, `order_id`, `payment_method`, `payment_status`, `amount_paid`, `transaction_id`, `created_at`, `updated_at`
) VALUES
      (1, 1, 'CREDIT_CARD',   'COMPLETED',  100.00, 'TXN-1001', NOW(), NOW()),
      (2, 2, 'PAYPAL',        'PENDING',    200.00, 'TXN-1002', NOW(), NOW()),
      (3, 3, 'CREDIT_CARD',   'COMPLETED',  300.00, 'TXN-1003', NOW(), NOW()),
      (4, 4, 'BANK_TRANSFER', 'COMPLETED',  400.00, 'TXN-1004', NOW(), NOW()),
      (5, 5, 'CREDIT_CARD',   'REFUNDED',   150.00, 'TXN-1005', NOW(), NOW());

-- =======================================
-- 6. SHIPPING_DETAILS
-- =======================================
INSERT INTO `shipping_details` (
    `id`, `order_id`, `address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`,
    `created_at`, `updated_at`
) VALUES
      (1, 1, '123 Main St',      'Apt 1',     'New York',      'NY', '10001', 'USA', NOW(), NOW()),
      (2, 2, '456 Park Ave',     '',          'Chicago',       'IL', '60601', 'USA', NOW(), NOW()),
      (3, 3, '789 Market St',    'Suite 300', 'San Francisco', 'CA', '94103', 'USA', NOW(), NOW()),
      (4, 4, '111 Maple Rd',     'Unit 10',   'Seattle',       'WA', '98101', 'USA', NOW(), NOW()),
      (5, 5, '222 High St',      'Floor 2',   'Boston',        'MA', '02115', 'USA', NOW(), NOW());

-- =======================================
-- 7. ORDER_STATUS_HISTORY
-- =======================================
INSERT INTO `order_status_history` (
    `id`, `order_id`, `old_status`, `new_status`, `changed_at`, `changed_by`
) VALUES
      (1, 1, 'PENDING',    'PROCESSING', '2024-01-10 10:00:00', 'system'),
      (2, 2, 'PROCESSING', 'SHIPPED',    '2024-01-11 15:00:00', 'system'),
      (3, 3, 'SHIPPED',    'DELIVERED',  '2024-01-12 16:00:00', 'system'),
      (4, 4, 'DELIVERED',  'COMPLETED',  '2024-01-13 17:00:00', 'system'),
      (5, 5, 'PENDING',    'CANCELLED',  '2024-01-14 18:00:00', 'system');

-- =======================================
-- 8. INVOICES
-- =======================================
INSERT INTO `invoices` (
    `id`, `order_id`, `invoice_number`, `invoice_date`, `due_date`, `total_amount`, `created_at`, `updated_at`
) VALUES
      (1, 1, 'INV-1001', '2024-01-10 10:00:00', '2024-01-20 23:59:59', 100.00, NOW(), NOW()),
      (2, 2, 'INV-1002', '2024-01-11 10:30:00', '2024-01-21 23:59:59', 200.00, NOW(), NOW()),
      (3, 3, 'INV-1003', '2024-01-12 11:45:00', '2024-01-22 23:59:59', 300.00, NOW(), NOW()),
      (4, 4, 'INV-1004', '2024-01-13 12:15:00', '2024-01-23 23:59:59', 400.00, NOW(), NOW()),
      (5, 5, 'INV-1005', '2024-01-14 13:25:00', '2024-01-24 23:59:59', 150.00, NOW(), NOW());

-- =======================================
-- 9. REFUNDS
-- =======================================
INSERT INTO `refunds` (
    `id`, `order_id`, `refund_amount`, `refund_reason`, `processed_by`, `processed_at`
) VALUES
      (1, 5, 150.00, 'Customer requested cancellation', 'admin', '2024-01-15 09:00:00'),
      (2, 2,  20.00, 'Partial refund for damaged item', 'admin', '2024-01-12 09:00:00'),
      (3, 3,  30.00, 'Price adjustment',               'admin', '2024-01-13 09:00:00'),
      (4, 4,  50.00, 'Shipping overcharge refund',     'admin', '2024-01-14 09:00:00'),
      (5, 1,  10.00, 'Loyalty discount after purchase', 'admin', '2024-01-15 09:00:00');

-- =======================================
-- 10. ORDER_DISCOUNTS
-- =======================================
INSERT INTO `order_discounts` (
    `id`, `order_id`, `discount_id`, `discount_amount`, `applied_at`
) VALUES
      (1, 1, 1, 10.00, '2024-01-10 09:30:00'),
      (2, 2, 2,  5.00, '2024-01-11 10:45:00'),
      (3, 3, 3, 45.00, '2024-01-12 12:00:00'),
      (4, 4, 4,  0.00, '2024-01-13 12:30:00'),  -- FREESHIP
      (5, 5, 5, 30.00, '2024-01-14 13:45:00');
