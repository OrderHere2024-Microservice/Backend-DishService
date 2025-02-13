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
                      updated_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
                          category_id   serial PRIMARY KEY NOT NULL UNIQUE,
                          restaurant_id integer            NOT NULL,
                          category_name varchar(255)       NOT NULL UNIQUE,
                          created_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_time  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
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