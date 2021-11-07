package com.learn.chapter09.pagePlugin;

/**
 * 标题：分页参数<br>
 * 描述：分页参数<br>
 **/
public class PageParams {

    private Integer page; // 当前页码
    private Integer pageSize; // 分页条数
    private Boolean useFlag; // 是否启动插件
    private Boolean checkFlag; // 是否检测当前页码的有效性
    private Integer total;  // 当前SQL返回总数，插件回填
    private Integer totalPage; // SQL以当前分页的总页数，插件回填

    @Override
    public String toString() {
        return "PageParams{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", useFlag=" + useFlag +
                ", checkFlag=" + checkFlag +
                ", total=" + total +
                ", totalPage=" + totalPage +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(Boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
