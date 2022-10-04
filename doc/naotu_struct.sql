DROP TABLE IF EXISTS `map_info`;
CREATE TABLE `map_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `map_id` varchar(30) NOT NULL COMMENT '导图ID',
  `title` varchar(30) NOT NULL COMMENT '导图名字',
  `content` varchar(10000) NOT NULL COMMENT '导图数据',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mp_code` (`map_id`),
  UNIQUE KEY `db_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='导图数据';