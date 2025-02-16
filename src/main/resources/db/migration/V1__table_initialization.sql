CREATE TABLE category (
                          category_id   serial PRIMARY KEY NOT NULL UNIQUE,
                          restaurant_id integer            NOT NULL,
                          category_name varchar(255)       NOT NULL UNIQUE,
                          created_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE dish (
                      dish_id       serial PRIMARY KEY NOT NULL UNIQUE,
                      restaurant_id integer            NOT NULL,
                      dish_name     varchar(255)       NOT NULL,
                      description   varchar(255)       NOT NULL,
                      price         decimal(5, 2)      NOT NULL,
                      image_url     varchar(255)       NOT NULL,
                      category_id   integer            NOT NULL REFERENCES category(category_id),
                      rating        decimal(3, 1),
                      availability  boolean            NOT NULL DEFAULT true,
                      created_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE ingredient (
                            ingredient_id serial PRIMARY KEY NOT NULL UNIQUE,
                            name          varchar(255)       NOT NULL,
                            unit          varchar(50)        NOT NULL,
                            created_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE link_ingredient_dish (
                                      link_ingredient_dish_id serial PRIMARY KEY NOT NULL UNIQUE,
                                      dish_id                 integer           NOT NULL REFERENCES dish(dish_id),
                                      ingredient_id           integer           NOT NULL REFERENCES ingredient(ingredient_id),
                                      quantity_value          decimal(10, 2)    NOT NULL,
                                      quantity_unit           varchar(50)       NOT NULL,
                                      created_time            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_time            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

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

INSERT INTO ingredient (name, unit)
VALUES ('Sugar', 'grams'),
       ('Flour', 'grams'),
       ('Butter', 'grams'),
       ('Eggs', 'pieces');


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

