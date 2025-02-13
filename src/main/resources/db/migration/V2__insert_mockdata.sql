INSERT INTO ingredient (name, unit)
VALUES ('Sugar', 'grams'),
       ('Flour', 'grams'),
       ('Butter', 'grams'),
       ('Eggs', 'pieces');

INSERT INTO category (restaurant_id, category_name)
VALUES (1, 'Main Dishes'),
       (1, 'Soups'),
       (1, 'Salads'),
       (1, 'Desserts'),
       (1, 'Beverages'),
       (1, 'Appetizers'),
       (1, 'Side Dishes'),
       (1, 'Breakfast'),
       (1, 'Lunch'),
       (1, 'Dinner');


INSERT INTO dish (restaurant_id, dish_name, description, price, image_url, rating, availability, category_id)
VALUES (1, 'Pasta Carbonara', 'Classic Italian pasta dish with bacon and eggs', 12.99,
        'https://plus.unsplash.com/premium_photo-1671547330493-b07d377085ca?auto=format&fit=crop&q=60&w=900&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGFzdGF8ZW58MHx8MHx8fDA%3D',
        4.5, true, 1),
       (1, 'Margherita Pizza', 'Traditional pizza with tomato sauce, mozzarella, and basil', 9.99,
        'https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&q=60&w=900&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cGl6emF8ZW58MHx8MHx8fDA%3D',
        4.2, true, 1),
       (1, 'Sushi Roll', 'Assorted sushi rolls with fresh seafood', 14.99,
        'https://plus.unsplash.com/premium_photo-1668143362936-ce8a84410659?auto=format&fit=crop&q=60&w=900&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8c3VzaGklMjByb2xsfGVufDB8fDB8fHww',
        4.7, true, 1),
       (1, 'Teriyaki Chicken', 'Grilled chicken with teriyaki sauce and steamed rice', 10.99,
        'https://images.unsplash.com/photo-1609183480237-ccbb2d7c5772?auto=format&fit=crop&q=60&w=900&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8VGVyaXlha2klMjBDaGlja2VufGVufDB8fDB8fHww',
        4.0, true, 1);

INSERT INTO link_ingredient_dish (dish_id, ingredient_id, quantity_value, quantity_unit)
VALUES (1, 1, 200, 'grams'),
       (1, 2, 50, 'grams'),
       (2, 3, 100, 'grams'),
       (2, 4, 150, 'grams'),
       (3, 1, 200, 'grams'),
       (3, 2, 50, 'grams'),
       (4, 3, 100, 'grams'),
       (4, 4, 150, 'grams');

