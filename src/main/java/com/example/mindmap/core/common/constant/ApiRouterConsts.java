package com.example.mindmap.core.common.constant;

public class ApiRouterConsts {
    private ApiRouterConsts() {
        throw new IllegalStateException("实例常量化失败");
    }

    /**
     * API请求路径前缀
     */
    public static final String API_URL_PREFIX = "/api";

    /**
     * 书籍搜索模块请求路径前缀
     */
    public static final String BOOK_URL_PREFIX = API_URL_PREFIX + "/book";

    /**
     * 脑图管理模块请求路径前缀
     */
    public static final String MIND_MAP_URL_PREFIX = API_URL_PREFIX + "/mind_map";

    /**
     * es搜索前缀
     */
    public static final String ES_URL_PREFIX = API_URL_PREFIX + "/es";

    /**
     * 资源（图片）模块请求路径前缀
     */
    public static final String RESOURCE_URL_PREFIX = "/image";


}
