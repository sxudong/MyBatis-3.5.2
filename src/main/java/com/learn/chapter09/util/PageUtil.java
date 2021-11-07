package com.learn.chapter09.util;

/**
 * 分页参数的传递
 *
 * 分页参数就是 offset 和 limit。 可以使用 RowBounds 来进行传递， 但是这样需要
 * 对原有的方法进行修改。 因此， 本例子通过 ThreadLocal 进行无痛觉的传递。
 */
public class PageUtil {
    private static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();

    public static void setPagingParam(int offset, int limit) {
        Page page = new Page(offset, limit);
        LOCAL_PAGE.set(page);
    }

    public static void removePagingParam() {
        LOCAL_PAGE.remove();
    }

    public static Page getPaingParam() {
        return LOCAL_PAGE.get();
    }

}