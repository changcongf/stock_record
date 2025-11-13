CREATE TABLE `t_daily_record`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `current_day` date     NOT NULL COMMENT '当前日期，格式：YYYY-MM-DD',
    `content`     text COLLATE utf8mb4_unicode_ci,
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_current_day` (`current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_company_record`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `current_day` date                                    NOT NULL COMMENT '当前日期，格式：YYYY-MM-DD',
    `company`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称或标识',
    `content`     text COLLATE utf8mb4_unicode_ci,
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_company_day` (`company`,`current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_industry_record`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `current_day` date                                    NOT NULL COMMENT '当前日期，格式：YYYY-MM-DD',
    `industry`    varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行业',
    `content`     text COLLATE utf8mb4_unicode_ci,
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `type`        varchar(40),
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_industry_day` (`industry`,`current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_product_record`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `current_day` date                                    NOT NULL COMMENT '当前日期，格式：YYYY-MM-DD',
    `product`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产业',
    `content`     text COLLATE utf8mb4_unicode_ci,
    `create_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_day` (`product`,`current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_daily_comment`
(
    `id`              bigint unsigned NOT NULL AUTO_INCREMENT,
    `daily_record_id` bigint unsigned NOT NULL COMMENT '关联 t_daily_record.id',
    `current_day`     date                            NOT NULL,
    `content`         text COLLATE utf8mb4_unicode_ci NOT NULL,
    `create_time`     datetime                        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime                        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    KEY               `idx_daily_record_id` (`daily_record_id`),
    UNIQUE KEY `uk_current_day` (`current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_company_comment`
(
    `id`                bigint unsigned NOT NULL AUTO_INCREMENT,
    `company_record_id` bigint unsigned NOT NULL COMMENT '关联 t_company_record.id',
    `company`           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称或标识',
    `current_day`       date                                    NOT NULL,
    `content`           text COLLATE utf8mb4_unicode_ci         NOT NULL,
    `create_time`       datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    KEY                 `idx_company_record_id` (`company_record_id`),
    UNIQUE KEY `uk_company_day` (`company`, `current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_industry_comment`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT,
    `industry_record_id` bigint unsigned NOT NULL COMMENT '关联 t_industry_record.id',
    `industry`           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行业',
    `current_day`        date                                    NOT NULL,
    `content`            text COLLATE utf8mb4_unicode_ci         NOT NULL,
    `create_time`        datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`        datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    KEY                  `idx_industry_record_id` (`industry_record_id`),
    UNIQUE KEY `uk_industry_day` (`industry`, `current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `t_product_comment`
(
    `id`                bigint unsigned NOT NULL AUTO_INCREMENT,
    `product_record_id` bigint unsigned NOT NULL COMMENT '关联 t_product_record.id',
    `product`           varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称或标识',
    `current_day`       date                                    NOT NULL,
    `content`           text COLLATE utf8mb4_unicode_ci         NOT NULL,
    `create_time`       datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `info`        text COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`),
    KEY                 `idx_product_record_id` (`product_record_id`),
    UNIQUE KEY `uk_product_day` (`product`, `current_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `t_attention`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL COMMENT '名字',
    `type`        varchar(255) NOT NULL COMMENT '类型,包含industy, company, product',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `times`       int          NOT NULL COMMENT '次数',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;