package com.guo.etc.kernel.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Administrator on 2016/3/23.
 */
@Entity
public class Publisher {
    private int publisherId;
    private String publisherName;
    //定义元素类型为Book的关联集合属性books
    private Set<Book> book = new HashSet<Book>();

    @OneToMany(mappedBy = "publisher",fetch = FetchType.EAGER)
    @Cascade(value = {CascadeType.DELETE})
    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false, insertable = true, updatable = true)
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "publisher_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (publisherId != publisher.publisherId) return false;
        if (publisherName != null ? !publisherName.equals(publisher.publisherName) : publisher.publisherName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publisherId;
        result = 31 * result + (publisherName != null ? publisherName.hashCode() : 0);
        return result;
    }
}
