package com.member.cozastore.entity.combinedKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductOrderCombinedKey implements Serializable {
    @Column(name = "idProduct",nullable = false)
    private int idProduct;

    @Column(name = "idOrder",nullable = false)
    private int idOrder;

    public static class BlogTagKey implements Serializable {

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
}
