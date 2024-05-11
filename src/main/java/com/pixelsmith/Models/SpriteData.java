package com.pixelsmith.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pixelsmith.pathSerializer;
import jakarta.persistence.*;



@Entity
public class SpriteData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spriteDataId;
    @JsonSerialize(using = pathSerializer.class)
    private String pathDirect;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spriteId", referencedColumnName = "spriteId")
    @JsonBackReference
    private Sprite sprite;

    // Getters and setters

    public Integer getSpriteDataId() {
        return spriteDataId;
    }

    public void setSpriteDataId(Integer spriteDataId) {
        this.spriteDataId = spriteDataId;
    }

    public String getPathDirect() {
        return pathDirect;
    }

    public void setPathDirect(String pathDirect) {
        this.pathDirect = pathDirect;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
