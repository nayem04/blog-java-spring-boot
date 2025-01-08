package com.blogjavaspringboot.domain.entities;

import com.blogjavaspringboot.common.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    /*The @Lob annotation in Java Persistence API (JPA) is used to mark a field
    as a "Large Object" (LOB) in the database. It indicates that
    the data stored in this field can be large, and therefore,
    special handling may be required for storing and retrieving it.

    In JPA, there are two types of large objects:
        CLOB (Character Large Object): For storing large text data (e.g., long descriptions, HTML content, etc.).
        BLOB (Binary Large Object): For storing large binary data (e.g., images, files, etc.).

    Usage of @Lob:
    The @Lob annotation can be applied to either a String (for CLOB) or a byte[] (for BLOB).

    When to Use @Lob:
    For text fields: If you expect a field to store large amounts of text
        (such as long blog posts, detailed descriptions, or comments),
        you would use @Lob with a String field.
    For binary data: If you expect a field to store binary data
        (such as images, videos, or files), you would use @Lob with a byte[] or Byte[].*/
    //@Lob
    @Column(name = "content", nullable = false, length = 10000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
