package com.zhang.pojo;

import java.io.Serializable;

public class BlogType implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键
    private Integer id;
    //类型名称
    private String typeName;
    //序号
    private Integer orderNum;
    //该类型下博客名称
    private Integer blogCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }
}
