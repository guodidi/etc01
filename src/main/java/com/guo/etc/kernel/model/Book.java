package com.guo.etc.kernel.model;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/3/23.
 */
@Entity
@Table(name = "book")
public class Book {
    private int bookId;
    private String bookTitle;
    private String bookAuthor;

    /**
     *添加外键Pbulisher
     *使用@ManyToOne 和@JoinColumn注解实现Books到Publisher的多对一关联
     */
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "book_publisher_id")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    //增加构造器
    public Book() {
    }

    public Book(String bookAuthor, String bookTitle) {
        this.bookAuthor = bookAuthor;
        this.bookTitle = bookTitle;
    }

    public Book(String bookTitle, Publisher publisher, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.bookAuthor = bookAuthor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, insertable = true, updatable = true)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "book_title", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Basic
    @Column(name = "book_author", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (bookTitle != null ? !bookTitle.equals(book.bookTitle) : book.bookTitle != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(book.bookAuthor) : book.bookAuthor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        return result;
    }
}
