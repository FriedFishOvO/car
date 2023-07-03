CREATE TABLE cars
(
    ID                      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    name                    VARCHAR(50) COMMENT '名称',
    brand                   VARCHAR(50) COMMENT '品牌',
    image                   VARCHAR(255) COMMENT '预览图',
    color                   VARCHAR(10) COMMENT '车身颜色',
    price                   DECIMAL(5, 2) COMMENT '价格',
    displacement            DECIMAL(4, 2) COMMENT '排量',
    transmission            VARCHAR(10) COMMENT '变速箱',
    model                   VARCHAR(10) COMMENT '车型',
    capacity                VARCHAR(10) COMMENT '座位数',
    fuel_type               VARCHAR(10) COMMENT '能源类型',
    fuel_consumption        DECIMAL(5, 2) COMMENT '综合油耗',
    battery_range           INT COMMENT '综合油耗',
    first_registration_date DATE COMMENT '首次上牌时间',
    registration_area       VARCHAR(20) COMMENT '上牌地区',
    age                     INT COMMENT '车龄',
    odometer                INT COMMENT '表显里程',
    is_deleted              TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记'
);

CREATE TABLE users
(
    ID         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    nickname   VARCHAR(50) COMMENT '昵称',
    avatar     VARCHAR(255) COMMENT '头像',
    username   VARCHAR(50) COMMENT '用户名',
    password   VARCHAR(50) COMMENT '密码',
    type       TINYINT(1) COMMENT '用户类型, 0管理员, 1普通用户',
    is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记'
);

CREATE TABLE user_car
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏记录ID',
    user_id    BIGINT COMMENT '用户ID',
    car_id     BIGINT COMMENT '汽车ID',
    is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标记'
);