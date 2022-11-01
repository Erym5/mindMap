package com.example.mindmap.core.common.constant;

public class CacheConsts {
    /**
     * 本项目 Redis 缓存前缀
     */
    public static final String REDIS_CACHE_PREFIX = "Cache::MindMap::";

    /**
     * Redis 缓存管理器
     */
    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";

    /**
     * 用户信息缓存
     */
    public static final String USER_INFO_CACHE_NAME = "userInfoCache";

    public enum CacheEnum {

        USER_INFO_CACHE(2, USER_INFO_CACHE_NAME, 60 * 60 * 24, 10000);


        /**
         * 缓存类型 0-本地 1-本地和远程 2-远程
         */
        private int type;
        /**
         * 缓存的名字
         */
        private String name;
        /**
         * 失效时间（秒） 0-永不失效
         */
        private int ttl;
        /**
         * 最大容量
         */
        private int maxSize;

        CacheEnum(int type, String name, int ttl, int maxSize) {
            this.type = type;
            this.name = name;
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public boolean isLocal() {
            return type <= 1;
        }

        public boolean isRemote() {
            return type >= 1;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }

        public int getMaxSize() {
            return maxSize;
        }

    }
}
