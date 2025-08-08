INSERT INTO CATEGORY (id, name, description) VALUES (1, 'Beverages', 'Drinks and juices');
INSERT INTO CATEGORY (id, name, description) VALUES (2, 'Snacks', 'Chips and biscuits');

INSERT INTO PRODUCT (id, sku, name, description, category_id, price, stock) VALUES
  (1, 'BEV-001', 'Orange Juice', '1L bottle', 1, 12.50, 100),
  (2, 'SNK-001', 'Potato Chips', 'Salted 200g', 2, 8.90, 200);

INSERT INTO SUPPLIER (id, name, contact, phone, address) VALUES (1, 'Fresh Supply Co.', 'Alice', '13800000001', 'No.1 Road');
INSERT INTO CUSTOMER (id, name, phone, address) VALUES (1, 'Bob', '13900000002', 'Street 2');