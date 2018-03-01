INSERT INTO category (name, description, image_url, is_active)
VALUES ('Телевизоры', 'Описание для телевизоров', 'CAT_1.png', TRUE);
INSERT INTO category (name, description, image_url, is_active)
VALUES ('Пылесосы', 'Описание для пылесосов', 'CAT_2.png', TRUE);
INSERT INTO category (name, description, image_url, is_active) VALUES ('Фены', 'Описание для фенов', 'CAT_3.png', TRUE);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Иван', 'Мохов', 'ADMIN', TRUE, '$2a$10$vphtCLZ2NhrJl8yZvLsTo.E9Tzgikoi/uZJanusfgYb8FElQR3By.', 'mox@gmail.com', '+79626662489');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Николай', 'Трыков', 'SUPPLIER', TRUE, '$2a$10$Pzq3NKLbwmVsXVmJ7QjAhesAsu9tGbE5P37R6dnvsH7XbiyispGJS', 'trik@gmail.com', '+79521112233');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Андрей', 'Новов', 'SUPPLIER', TRUE, '$2a$10$Pzq3NKLbwmVsXVmJ7QjAhesAsu9tGbE5P37R6dnvsH7XbiyispGJS', 'novand@gmail.com', '+9015547488');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Роман', 'Дьяченко', 'SUPPLIER', TRUE, '$2a$10$Pzq3NKLbwmVsXVmJ7QjAhesAsu9tGbE5P37R6dnvsH7XbiyispGJS', 'romanbanan@gmail.com', '+79045513399');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Иван', 'Коновалов', 'USER', TRUE, '$2a$10$Pzq3NKLbwmVsXVmJ7QjAhesAsu9tGbE5P37R6dnvsH7XbiyispGJS', 'ivan@gmail.com', '+79065513399');


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123TVS', '19LES76T2', 'Erisson',
        'Телевизор Erisson 19LES76T2 черного цвета идеально подойдет для вашей кухни или небольшого помещения.'
  , 7310, 5, TRUE, 1, 2);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDQWE123TVS', '32LJ519U', 'LG',
        'LG 32LJ519U - это отличный HD-телевизор для всей семьи, который успешно совместит в себе все функции, присущие полноценному развлекательному медиацентру.'
  , 15190, 5, TRUE, 1, 2);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDRTY123TVS', 'STV-LC22T890FL', 'Supra',
        'Телевизор Supra STV-LC22T890FL успешно совмещает в себе все функции, присущие полноценному развлекательному медиацентру.'
  , 8990, 5, TRUE, 1, 2);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123PIL', 'FC 8760', 'Philips',
        'Благодаря технологии PowerCyclone новый пылесос Philips помогает делать уборку безо всяких усилий.'
  , 10070, 5, TRUE, 2, 3);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDQWE123PIL', 'V-K89682HU', 'LG',
        'Мощный пылесос LG V-K89682HU разработан специально для деликатной и качественной уборки внутренних помещений.'
  , 9770, 5, TRUE, 2, 3);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDRTY123PIL', 'FC 6141', 'Philips',
        'Великолепные результаты уборки благодаря мощному пылесосу Philips MiniVac для сухой и влажной уборки.'
  , 3010, 5, TRUE, 2, 3);

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123FEN', 'D3010', 'Remington',
        'Отличный и надежный фен.'
  , 1700, 5, TRUE, 3, 4);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDQWE123FEN', 'D212E', 'BaByliss',
        'Маленький, компактный и мощный фен BaByliss D212E легко и быстро высушит ваши волосы.'
  , 1930, 5, TRUE, 3, 4);
INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDRTY123FEN', '554.13', 'Valera',
        'Компактный фен Valera Swiss Bebe 554.13 создан специально для деликатного ухода за детскими волосами и телом.'
  , 1270, 0, TRUE, 3, 4);

INSERT INTO cart (user_id, grand_total, cart_lines)
VALUES (5, 0, 0);

INSERT INTO address (user_id, address_line_one, address_line_two, city, state, country, postal_code, is_billing, is_shipping)
VALUES (5, 'ул. Петра-водкна', 'д45, кв 33', 'Пушкин', 'Ленинградская-область', 'Россия', 188300, true, false);