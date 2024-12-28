-- V4__fill_with_data.sql

-- =====================================
-- 1) Insert data into warehouses
-- =====================================
INSERT INTO warehouses (warehouse_name, location, created_at, updated_at)
VALUES
    ('Main Warehouse', '123 Main St', NOW(), NOW()),
    ('East Warehouse', '45 East Ave', NOW(), NOW()),
    ('West Warehouse', '78 West Blvd', NOW(), NOW()),
    ('North Warehouse', '99 North Rd', NOW(), NOW()),
    ('Overflow Warehouse', '101 Overflow Dr', NOW(), NOW());

-- =====================================
-- 2) Insert data into bins
--    Make sure the warehouse_id references existing warehouses (1-5)
-- =====================================
INSERT INTO bins (warehouse_id, bin_name, bin_type, created_at, updated_at)
VALUES
    (1, 'BIN-A1', 'PICKING', NOW(), NOW()),
    (1, 'BIN-A2', 'STORAGE', NOW(), NOW()),
    (2, 'BIN-B1', 'PICKING', NOW(), NOW()),
    (3, 'BIN-C1', 'STORAGE', NOW(), NOW()),
    (5, 'BIN-O1', 'OVERFLOW', NOW(), NOW());

-- =====================================
-- 3) Insert data into stock_levels
--    product_id is just an example reference (1-5).
--    bin_id references existing bins (1-5),
--    warehouse_id references existing warehouses (1-5)
-- =====================================
INSERT INTO stock_levels (product_id, warehouse_id, bin_id, quantity_on_hand, quantity_reserved, created_at, updated_at)
VALUES
    (1, 1, 1, 100, 10, NOW(), NOW()),
    (2, 1, 2, 50, 5, NOW(), NOW()),
    (3, 2, 3, 200, 20, NOW(), NOW()),
    (4, 3, 4, 80, 0, NOW(), NOW()),
    (5, 5, 5, 500, 50, NOW(), NOW());

-- =====================================
-- 4) Insert data into suppliers
-- =====================================
INSERT INTO suppliers (supplier_name, contact_person, phone, email, address, created_at, updated_at)
VALUES
    ('Supplier A', 'Alice', '+1-555-1234', 'alice@supplierA.com', '100 Park Ave', NOW(), NOW()),
    ('Supplier B', 'Bob', '+1-555-5678', 'bob@supplierB.com', '200 Market St', NOW(), NOW()),
    ('Supplier C', 'Charlie', '+1-555-9999', 'charlie@supplierC.com', '300 Center Rd', NOW(), NOW()),
    ('Supplier D', 'Diana', '+1-555-1212', 'diana@supplierD.com', '400 Broad St', NOW(), NOW()),
    ('Supplier E', 'Ethan', '+1-555-8888', 'ethan@supplierE.com', '500 Lakeview Dr', NOW(), NOW());

-- =====================================
-- 5) Insert data into purchase_orders
--    supplier_id references existing suppliers (1-5)
-- =====================================
INSERT INTO purchase_orders (supplier_id, order_date, expected_delivery_date, status, total_cost, created_at, updated_at)
VALUES
    (1, NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 'PENDING', 1500.00, NOW(), NOW()),
    (2, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'APPROVED', 2200.50, NOW(), NOW()),
    (3, NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 'RECEIVED', 999.99, NOW(), NOW()),
    (4, NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 'PENDING', 4530.25, NOW(), NOW()),
    (5, NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 'APPROVED', 1200.00, NOW(), NOW());

-- =====================================
-- 6) Insert data into purchase_order_items
--    purchase_order_id references purchase_orders (1-5)
--    product_id is an example reference (1-5)
-- =====================================
INSERT INTO purchase_order_items (purchase_order_id, product_id, quantity_ordered, unit_price, created_at, updated_at)
VALUES
    (1, 1, 10, 100.00, NOW(), NOW()),
    (1, 2, 5, 150.00, NOW(), NOW()),
    (2, 3, 20, 50.00, NOW(), NOW()),
    (3, 4, 15, 66.66, NOW(), NOW()),
    (5, 5, 12, 100.00, NOW(), NOW());

-- =====================================
-- 7) Insert data into stock_movements
--    product_id is an example reference (1-5)
--    warehouse_id references warehouses (1-5)
--    bin_id references bins (1-5)
--    movement_type can be e.g. 'RECEIPT', 'ISSUE', 'TRANSFER', 'ADJUSTMENT'
-- =====================================
INSERT INTO stock_movements (product_id, warehouse_id, bin_id, movement_type, quantity, movement_date, reference_doc, created_at, updated_at)
VALUES
    (1, 1, 1, 'RECEIPT', 50, NOW(), 'PO#1', NOW(), NOW()),
    (2, 1, 2, 'ISSUE', 10, NOW(), 'SO#101', NOW(), NOW()),
    (3, 2, 3, 'TRANSFER', 25, NOW(), 'TRANSFER#1001', NOW(), NOW()),
    (4, 3, 4, 'ADJUSTMENT', -5, NOW(), 'ADJ#5001', NOW(), NOW()),
    (5, 5, 5, 'RECEIPT', 100, NOW(), 'PO#2', NOW(), NOW());

-- =====================================
-- 8) Insert data into adjustment_reasons
-- =====================================
INSERT INTO adjustment_reasons (reason_code, description, created_at, updated_at)
VALUES
    ('DAMAGED', 'Goods were damaged', NOW(), NOW()),
    ('LOST', 'Goods lost in warehouse', NOW(), NOW()),
    ('COUNT_ERROR', 'Inventory count discrepancy', NOW(), NOW()),
    ('EXPIRED', 'Product expiration', NOW(), NOW()),
    ('TESTING', 'Used for testing samples', NOW(), NOW());

-- =====================================
-- 9) Insert data into inventory_transfers
--    from_warehouse_id and to_warehouse_id reference warehouses (1-5)
-- =====================================
INSERT INTO inventory_transfers (from_warehouse_id, to_warehouse_id, transfer_date, status, created_at, updated_at)
VALUES
    (1, 2, NOW(), 'INITIATED', NOW(), NOW()),
    (2, 3, NOW(), 'IN_TRANSIT', NOW(), NOW()),
    (3, 4, NOW(), 'COMPLETED', NOW(), NOW()),
    (4, 5, NOW(), 'INITIATED', NOW(), NOW()),
    (5, 1, NOW(), 'COMPLETED', NOW(), NOW());

-- =====================================
-- 10) Insert data into transfer_items
--     inventory_transfer_id references inventory_transfers (1-5)
--     product_id is an example reference (1-5)
-- =====================================
INSERT INTO transfer_items (inventory_transfer_id, product_id, quantity, created_at, updated_at)
VALUES
    (1, 1, 10, NOW(), NOW()),
    (1, 2, 5, NOW(), NOW()),
    (2, 3, 20, NOW(), NOW()),
    (4, 4, 8, NOW(), NOW()),
    (5, 5, 12, NOW(), NOW());
