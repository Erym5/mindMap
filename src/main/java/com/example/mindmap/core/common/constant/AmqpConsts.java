package com.example.mindmap.core.common.constant;

public class AmqpConsts {

    /**
     * 脑图信息改变 MQ
     */
    public static class MapChangeMq {

        /**
         * 小说信息改变交换机
         */
        public static final String EXCHANGE_NAME = "EXCHANGE-MAP-CHANGE";

        /**
         * Elasticsearch book 索引更新的队列
         */
        public static final String QUEUE_DELETE = "QUEUE-MAP-DELETE";

        /**
         * Redis book 缓存更新的队列
         */
        public static final String QUEUE_REDIS_UPDATE = "QUEUE-REDIS-BOOK-UPDATE";

    }

}