package com.member.cozastore.entity;

import com.member.cozastore.entity.CompositeKey.BlogTagKey;
import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "blog_tag")
public class BlogTagEntity {

    @EmbeddedId
    private BlogTagKey blogTagKey;

    @ManyToOne
    @JoinColumn(name = "id_blog", insertable = false, updatable = false)
    private BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = "id_tag", insertable = false, updatable = false)
    private TagEntity tag;

    @Column(name = "create_date")
    private Date createDate;

    public BlogTagKey getBlogTagKey() {
        return blogTagKey;
    }

    public void setBlogTagKey(BlogTagKey blogTagKey) {
        this.blogTagKey = blogTagKey;
    }

    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
