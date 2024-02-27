package com.member.cozastore.entity.CompositeKey;

import jakarta.persistence.Column;

import java.io.Serializable;

public class BlogTagKey implements Serializable {

    @Column(name = "id_blog", nullable = false)
    private int idBlog;

    @Column(name = "id_tag", nullable = false)
    private int idTag;

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }
}
