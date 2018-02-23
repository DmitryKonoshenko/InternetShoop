INSERT INTO category (name, description, image_url, is_active)
VALUES ('Телевизоры', 'Описание для телевизоров', 'CAT_1.png', TRUE);
INSERT INTO category (name, description, image_url, is_active)
VALUES ('Пылесосы', 'Описание для пылесосов', 'CAT_2.png', TRUE);
INSERT INTO category (name, description, image_url, is_active) VALUES ('Фены', 'Описание для фенов', 'CAT_3.png', TRUE);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number, cart_id)
VALUES ('Иван', 'Мохов', 'ADMIN', TRUE, 'admin', 'mox@gmail.com', '+79626662489', null);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number, cart_id)
VALUES ('Николай', 'Трыков', 'SUPPLIER', TRUE, '12345', 'trik@gmail.com', '+79521112233', null);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number, cart_id)
VALUES ('Андрей', 'Новов', 'SUPPLIER', TRUE, '12345', 'novand@gmail.com', '+9015547488', null);

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number, cart_id)
VALUES ('Роман', 'Дьяченко', 'SUPPLIER', TRUE, '12345', 'romanbanan@gmail.com', '+79045513399', null);

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