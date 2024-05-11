package com.pixelsmith.controllers;

import com.pixelsmith.Models.Sprite;
import com.pixelsmith.Models.SpriteData;
import com.pixelsmith.services.SpriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sprites")
public class SpriteController {
    @Autowired
    private SpriteService spriteService;

    @PostMapping("/create")
    public ResponseEntity<Sprite> createSprite(@RequestBody Sprite sprite) {
        return ResponseEntity.ok(spriteService.createSprite(sprite));
    }

    @PutMapping("/{spriteId}")
    public ResponseEntity<Sprite> updateSprite(@PathVariable Integer spriteId, @RequestBody Map<String, Object> spriteDetails) {
        String spriteName = (String) spriteDetails.get("name");
        String pathDirect = (String) spriteDetails.get("pathDirect");

        try {
            Sprite updatedSprite = spriteService.updateSprite(spriteId, spriteName, pathDirect);
            return ResponseEntity.ok(updatedSprite);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sprite>> getSpritesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(spriteService.getSpritesByUser(userId));
    }

    @GetMapping("/sprites/{spriteId}/data")
    public ResponseEntity<SpriteData> getSpriteData(@PathVariable Integer spriteId) {
        try {
            SpriteData spriteData = spriteService.getSpriteData(spriteId);
            return ResponseEntity.ok(spriteData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/findIdByName")
    public ResponseEntity<Integer> findSpriteIdByName(@RequestParam String name) {
        Integer spriteId = spriteService.findSpriteIdByName(name);
        if (spriteId != null) {
            return ResponseEntity.ok(spriteId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/sprites/{spriteId}/name")
    public ResponseEntity<String> getSpriteNameById(@PathVariable Integer spriteId) {
        try {
            String spriteName = spriteService.findSpriteNameById(spriteId);
            if (spriteName != null) {
                System.out.println("les go");
                return ResponseEntity.ok(spriteName);
            } else {
                System.out.println("Sprite not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("fuck");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch sprite name");
        }
    }

}