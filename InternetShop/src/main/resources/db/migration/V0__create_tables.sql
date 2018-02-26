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
  `password`       VARCHAR(100)      DEFAULT NULL,
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

CREATE TABLE `ishop`.`address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `address_line_one` varchar(100) DEFAULT NULL,
  `address_line_two` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `postal_code` varchar(50) DEFAULT NULL,
  `is_billing` tinyint(1) DEFAULT NULL,
  `is_shipping` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_address_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ishop`.`cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `grand_total` decimal(10,2) DEFAULT '0.00',
  `cart_lines` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_user_detail` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

