package com.pixelsmith.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sprites")
public class Sprite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spriteId;
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    private Date lastModifiedDate;
    private String name;
    @OneToOne(mappedBy = "sprite", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private SpriteData spriteData;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setSpriteData(SpriteData spriteData) {
        this.spriteData = spriteData;
        spriteData.setSprite(this);  // Important: synchronize both sides of the relationship
    }

    public SpriteData getSpriteData() {
        return spriteData;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSpriteId() {
        return spriteId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
