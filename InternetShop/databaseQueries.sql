CREATE TABLE `ishop`.`category` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(50)      DEFAULT NULL,
  `description` VARCHAR(255)     DEFAULT NULL,
  `image_url`   VARCHAR(50)      DEFAULT NULL,
  `is_active`   TINYINT(1)       DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO category (name, description, image_url, is_active)
VALUES ('Телевизоры', 'Описание для телевизоров', 'CAT_1.png', TRUE);
INSERT INTO category (name, description, image_url, is_active)
VALUES ('Пылесосы', 'Описание для пылесосов', 'CAT_2.png', TRUE);
INSERT INTO category (name, description, image_url, is_active) VALUES ('Фены', 'Описание для фенов', 'CAT_3.png', TRUE);

CREATE TABLE `ishop`.`user_detail` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `first_name`     VARCHAR(50)      DEFAULT NULL,
  `last_name`      VARCHAR(50)      DEFAULT NULL,
  `role`           VARCHAR(50)      DEFAULT NULL,
  `enabled`        TINYINT(1)       DEFAULT NULL,
  `password`       VARCHAR(50)      DEFAULT NULL,
  `email`          VARCHAR(100)     DEFAULT NULL,
  `contact_number` VARCHAR(15)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Иван', 'Мохов', 'ADMIN', TRUE, 'admin', 'mox@gmail.com', '+79626662489');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Николай', 'Трыков', 'SUPPLIER', TRUE, '12345', 'trik@gmail.com', '+79521112233');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Андрей', 'Новов', 'SUPPLIER', TRUE, '12345', 'novand@gmail.com', '+9015547488');

INSERT INTO user_detail (first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Роман', 'Дьяченко', 'SUPPLIER', TRUE, '12345', 'romanbanan@gmail.com', '+79045513399');

CREATE TABLE `ishop`.`product` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `code`        VARCHAR(20)      DEFAULT NULL,
  `name`        VARCHAR(50)      DEFAULT NULL,
  `brand`       VARCHAR(50)      DEFAULT NULL,
  `description` VARCHAR(255)     DEFAULT NULL,
  `unit_price`  DECIMAL(10, 2)   DEFAULT NULL,
  `quantity`    INT(11)          DEFAULT NULL,
  `is_active`    TINYINT(4)       DEFAULT NULL,
  `category_id` INT(11)          DEFAULT NULL,
  `supplier_id` INT(11)          DEFAULT NULL,
  `purchases`   INT(11)          DEFAULT '0',
  `views`       INT(11)          DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_suplier_id` FOREIGN KEY (`supplier_id`) REFERENCES `user_detail` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

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


CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `address_line_one` varchar(100) DEFAULT NULL,
  `address_line_two` varchar(100) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `is_billing` tinyint(4) DEFAULT NULL,
  `is_shipping` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_user_id_idx` (`user_id`),
  CONSTRAINT `fk_address_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8

