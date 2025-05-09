create sequence if not exists category_seq increment by 1;
create sequence if not exists product_seq increment by 50;

CREATE TABLE IF NOT EXISTS category (
    id INTEGER DEFAULT nextval('category_seq') PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
    id INTEGER DEFAULT nextval('product_seq') PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price NUMERIC(38,2),
    category_id INTEGER REFERENCES category(id)
);

