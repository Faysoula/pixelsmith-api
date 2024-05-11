package com.pixelsmith.services;



import com.pixelsmith.Models.Sprite;
import com.pixelsmith.Models.SpriteData;
import com.pixelsmith.repos.SpriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpriteService {
    @Autowired
    private SpriteRepository spriteRepository;

    public Sprite createSprite(Sprite sprite) {
        return spriteRepository.save(sprite);
    }

    @Transactional
    public Sprite saveSprite(Sprite sprite) {
        return spriteRepository.save(sprite);
    }

    @Transactional
    public Sprite updateSprite(Integer spriteId, String spriteName, String pathDirect) {
        Sprite sprite = spriteRepository.findById(spriteId)
                .orElseThrow(() -> new RuntimeException("Sprite not found with id: " + spriteId));

        sprite.setName(spriteName);
        if (sprite.getSpriteData() != null) {
            sprite.getSpriteData().setPathDirect(pathDirect);
        } else {
            SpriteData newSpriteData = new SpriteData();
            newSpriteData.setPathDirect(pathDirect);
            newSpriteData.setSprite(sprite);
            sprite.setSpriteData(newSpriteData);
        }

        return spriteRepository.save(sprite);
    }

    public Integer findSpriteIdByName(String name) {
        Sprite sprite = spriteRepository.findByName(name);
        if (sprite != null) {
            return sprite.getSpriteId();
        }
        return null; // Return null if the sprite is not found
    }

    public String findSpriteNameById(Integer id) {
        Sprite sprite = spriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprite not found with id: " + id));
        return sprite.getName();
    }

    @Transactional
    public SpriteData getSpriteData(Integer spriteId) {
        // Fetch the sprite by ID
        Sprite sprite = spriteRepository.findById(spriteId)
                .orElseThrow(() -> new RuntimeException("Sprite not found with id: " + spriteId));

        // Return the associated sprite data
        return sprite.getSpriteData();
    }

    public List<Sprite> getSpritesByUser(Integer userId) {
        return spriteRepository.findAllByUserUserId(userId);
    }
}
