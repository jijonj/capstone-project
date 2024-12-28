-- V3__add_all_tables.sql

-- ==================================
-- 1) warehouses
-- ==================================
CREATE TABLE IF NOT EXISTS warehouses (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          warehouse_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
    );

-- ==================================
-- 2) bins
-- ==================================
CREATE TABLE IF NOT EXISTS bins (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    warehouse_id INT NOT NULL,
                                    bin_name VARCHAR(255) NOT NULL,
    bin_type VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_bins_warehouse
    FOREIGN KEY (warehouse_id)
    REFERENCES warehouses (id)
    );

-- ==================================
-- 3) stock_levels
-- ==================================
CREATE TABLE IF NOT EXISTS stock_levels (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            product_id INT NOT NULL,
                                            warehouse_id INT NOT NULL,
                                            bin_id INT,
                                            quantity_on_hand INT NOT NULL,
                                            quantity_reserved INT NOT NULL,
                                            created_at DATETIME NOT NULL,
                                            updated_at DATETIME NOT NULL,
                                            CONSTRAINT fk_stock_levels_warehouse
                                            FOREIGN KEY (warehouse_id)
    REFERENCES warehouses (id),
    CONSTRAINT fk_stock_levels_bin
    FOREIGN KEY (bin_id)
    REFERENCES bins (id)
    );

-- ==================================
-- 4) suppliers
-- ==================================
CREATE TABLE IF NOT EXISTS suppliers (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         supplier_name VARCHAR(255) NOT NULL,
    contact_person VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255),
    address VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
    );

-- ==================================
-- 5) purchase_orders
-- ==================================
CREATE TABLE IF NOT EXISTS purchase_orders (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               supplier_id INT NOT NULL,
                                               order_date DATETIME NOT NULL,
                                               expected_delivery_date DATETIME,
                                               status VARCHAR(50) NOT NULL,
    total_cost DECIMAL(10, 2),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_purchase_orders_supplier
    FOREIGN KEY (supplier_id)
    REFERENCES suppliers (id)
    );

-- ==================================
-- 6) purchase_order_items
-- ==================================
CREATE TABLE IF NOT EXISTS purchase_order_items (
                                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                                    purchase_order_id INT NOT NULL,
                                                    product_id INT NOT NULL,
                                                    quantity_ordered INT NOT NULL,
                                                    unit_price DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_purchase_order_items_order
    FOREIGN KEY (purchase_order_id)
    REFERENCES purchase_orders (id)
    );

-- ==================================
-- 7) stock_movements
-- ==================================
CREATE TABLE IF NOT EXISTS stock_movements (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               product_id INT NOT NULL,
                                               warehouse_id INT NOT NULL,
                                               bin_id INT,
                                               movement_type VARCHAR(50) NOT NULL,  -- e.g., RECEIPT, ISSUE, TRANSFER, ADJUSTMENT
    quantity INT NOT NULL,
    movement_date DATETIME NOT NULL,
    reference_doc VARCHAR(255),          -- e.g., purchase order #, transfer doc
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_stock_movements_warehouse
    FOREIGN KEY (warehouse_id)
    REFERENCES warehouses (id),
    CONSTRAINT fk_stock_movements_bin
    FOREIGN KEY (bin_id)
    REFERENCES bins (id)
    );

-- ==================================
-- 8) adjustment_reasons
-- ==================================
CREATE TABLE IF NOT EXISTS adjustment_reasons (
                                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                                  reason_code VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
    );

-- ==================================
-- 9) inventory_transfers
-- ==================================
CREATE TABLE IF NOT EXISTS inventory_transfers (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                                   from_warehouse_id INT NOT NULL,
                                                   to_warehouse_id INT NOT NULL,
                                                   transfer_date DATETIME NOT NULL,
                                                   status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_inventory_transfers_from_warehouse
    FOREIGN KEY (from_warehouse_id)
    REFERENCES warehouses (id),
    CONSTRAINT fk_inventory_transfers_to_warehouse
    FOREIGN KEY (to_warehouse_id)
    REFERENCES warehouses (id)
    );

-- ==================================
-- 10) transfer_items
-- ==================================
CREATE TABLE IF NOT EXISTS transfer_items (
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              inventory_transfer_id INT NOT NULL,
                                              product_id INT NOT NULL,
                                              quantity INT NOT NULL,
                                              created_at DATETIME NOT NULL,
                                              updated_at DATETIME NOT NULL,
                                              CONSTRAINT fk_transfer_items_transfer
                                              FOREIGN KEY (inventory_transfer_id)
    REFERENCES inventory_transfers (id)
    );
