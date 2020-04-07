package com.example.library.common;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Audit {
    @CreatedBy
    @Column(name = "create_by", nullable = true, insertable = true)
    private String createBy;

    @LastModifiedBy
    @Column(name = "modified_by", nullable = true, insertable = true)
    private String modifiedBy;

    @CreatedDate
    @Column(name = "created_date", nullable = true, insertable = true, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = true, updatable = true)
    private Date modifiedDate;


    @PrePersist
    void onCreate() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }

    @PreUpdate
    void onPersist() {
        this.modifiedDate = new Date();
    }
}
