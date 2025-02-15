CREATE TABLE cash_flow_categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  icon VARCHAR(255),
  color VARCHAR(100),
  `order` INTEGER DEFAULT 5,
  category_father_id BIGINT DEFAULT NULL,
  enabled BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_father_id) REFERENCES cash_flow_categories(id) ON DELETE RESTRICT
);

INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(10, "Communication, PC", null, null, 10);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Internet", null, 15, 10),
("Cell phone", null, 15, 10),
("Software, apps, games", null, 15, 10);

INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(30, "Shopping", null, null, 30);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Clothes and shoes", null, 35, 30),
("Drug-store, chemist", null, 35, 30),
("Electronic, accessories", null, 35, 30),
("Free time", null, 35, 30),
("Grift, joy", null, 35, 30),
("Health and beauty", null, 35, 30),
("Home, garden", null, 35, 30),
("Jewels, accessories", null, 35, 30),
("Kids", null, 35, 30),
("Pets, animals", null, 35, 30),
("Stationery, tools", null, 35, 30);

INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(50, "Housing", null, null, 50);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Energy, utilities", null, 55, 50),
("Maintenance, repairs", null, 55, 50),
("Property insurance", null, 55, 50),
("Rent", null, 55, 50),
("Services", null, 55, 50);


INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(70, "Food & Drinks", null, null, 70);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Bar, cafe", null, 75, 70),
("Groceries", null, 75, 70),
("Restaurant, fast-food", null, 75, 70);


INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(90, "Transportation", null, null, 90);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Business trips", null, 95, 90),
("Long distance", null, 95, 90),
("Public transport", null, 95, 90),
("Taxi", null, 95, 90);


INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(110, "Life & Entertainment", null, null, 110);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Active sport, fitness", null, 115, 110),
("Alcohol, tabacco", null, 115, 110),
("Books, audio, subscriptions", null, 115, 110),
("Charity, gifts", null, 115, 110),
("Culture, sport events", null, 115, 110),
("Education, development", null, 115, 110),
("Health care, doctor", null, 115, 110),
("Hobbies", null, 115, 110),
("Holiday, trips, hotels", null, 115, 110),
("Life events", null, 115, 110),
("Lottery, gambling", null, 115, 110),
("TV, Streaming", null, 115, 110),
("Wellness, beauty", null, 115, 110);

INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(130, "Financial expenses", null, null, 130);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Advisory", null, 135, 130),
("Charges, Fees", null, 135, 130),
("Child Support", null, 135, 130),
("Fines", null, 135, 130),
("Insurances", null, 135, 130),
("Loan, interests", null, 135, 130),
("Taxes", null, 135, 130);


INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(150, "Investments", null, null, 150);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Collections", null, 155, 150),
("Financial Investments", null, 155, 150),
("Realty", null, 155, 150),
("Savings", null, 155, 150);


INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(170, "Income", null, null, 170);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Checks, coupons", null, 175, 170),
("Dues & grants", null, 175, 170),
("Gifts", null, 175, 170),
("Interests, dividends", null, 175, 170),
("Lending, renting", null, 175, 170),
("Lottery, gambling", null, 175, 170),
("Refunds (tax, purchases)", null, 175, 170),
("Rental income", null, 175, 170),
("Sale", null, 175, 170),
("Wage, invoice", null, 175, 170);

INSERT INTO cash_flow_categories(id, name, icon, color, `order`)
VALUES(190, "Others", null, null, 99999);

INSERT INTO cash_flow_categories(name, icon, `order`, category_father_id) VALUES
("Missing", null, 195, 190),
("Balance", null, 195, 190);
