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

CREATE TABLE `ishop`.`user_detail` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT,
  `first_name`     VARCHAR(50)      DEFAULT NULL,
  `last_name`      VARCHAR(50)      DEFAULT NULL,
  `role`           VARCHAR(50)      DEFAULT NULL,
  `enabled`        TINYINT(1)       DEFAULT NULL,
  `password`       VARCHAR(100)     DEFAULT NULL,
  `email`          VARCHAR(100)     DEFAULT NULL,
  `contact_number` VARCHAR(15)      DEFAULT NULL,

  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.`product` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `code`        VARCHAR(20)      DEFAULT NULL,
  `name`        VARCHAR(50)      DEFAULT NULL,
  `brand`       VARCHAR(50)      DEFAULT NULL,
  `description` VARCHAR(255)     DEFAULT NULL,
  `unit_price`  DECIMAL(10, 2)   DEFAULT NULL,
  `quantity`    INT(11)          DEFAULT NULL,
  `is_active`   TINYINT(4)       DEFAULT NULL,
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

CREATE TABLE `ishop`.`address` (
  `id`               INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`          INT(11)          DEFAULT NULL,
  `address_line_one` VARCHAR(100)     DEFAULT NULL,
  `address_line_two` VARCHAR(100)     DEFAULT NULL,
  `city`             VARCHAR(50)      DEFAULT NULL,
  `state`            VARCHAR(50)      DEFAULT NULL,
  `country`          VARCHAR(50)      DEFAULT NULL,
  `postal_code`      VARCHAR(50)      DEFAULT NULL,
  `is_billing`       TINYINT(1)       DEFAULT NULL,
  `is_shipping`      TINYINT(1)       DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_address_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.`cart` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)          DEFAULT NULL,
  `grand_total` DECIMAL(10, 2)   DEFAULT '0.00',
  `cart_lines`  INT(11)          DEFAULT NULL,
  `discount_id` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_user_detail` (`user_id`),
  CONSTRAINT `fk_cart_user_detail` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.cart_line (
  `id`            INT(11) NOT NULL AUTO_INCREMENT,
  `cart_id`       INT              DEFAULT NULL,
  `total`         DECIMAL(10, 2)   DEFAULT '0.00',
  `product_id`    INT(11)          DEFAULT NULL,
  `product_count` INT(11)          DEFAULT NULL,
  `buying_price`  DECIMAL(10, 2)   DEFAULT '0.00',
  `is_available`  TINYINT(1)       DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_cartline_cart_id FOREIGN KEY (`cart_id`) REFERENCES cart (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_cartline_product_id FOREIGN KEY (`product_id`) REFERENCES product (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.`order_detail` (
  `id`               INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`          INT(11)          DEFAULT NULL,
  `order_total`      DECIMAL(10, 2)   DEFAULT NULL,
  `order_count`      INT(11)          DEFAULT NULL,
  `shipping_id`      INT(11)          DEFAULT NULL,
  `billing_id`       INT(11)          DEFAULT NULL,
  `order_date`       DATETIME         DEFAULT NULL,
  `is_pay`           TINYINT(1)       DEFAULT '0',
  `delivery`         TINYINT(1)       DEFAULT '0',
  `is_delivery`      TINYINT(1)       DEFAULT '0',
  `is_shipped_order` TINYINT(1)       DEFAULT '0',
  `discount`         INT(11)          DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_order_detail_user_id` (`user_id`),
  KEY `fk_order_detail_shipping_id` (`shipping_id`),
  KEY `fk_order_detail_billing_id` (`billing_id`),
  CONSTRAINT `fk_order_detail_billing_id` FOREIGN KEY (`billing_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_order_detail_shipping_id` FOREIGN KEY (`shipping_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_order_detail_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.`order_item` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT,
  `order_id`      INT(11)          DEFAULT NULL,
  `total`         DECIMAL(10, 2)   DEFAULT NULL,
  `product_id`    INT(11)          DEFAULT NULL,
  `product_count` INT(11)          DEFAULT NULL,
  `buying_price`  DECIMAL(10, 2)   DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_item_product_id` (`product_id`),
  KEY `fk_order_item_order_id` (`order_id`),
  CONSTRAINT `fk_order_item_order_id` FOREIGN KEY (`order_id`) REFERENCES `order_detail` (`id`),
  CONSTRAINT `fk_order_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `ishop`.`promocode` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `code`     VARCHAR(50)      DEFAULT NULL,
  `discount` INT(11)          DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8