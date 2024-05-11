package com.pixelsmith.repos;


import com.pixelsmith.Models.Sprite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpriteRepository extends JpaRepository<Sprite, Integer> {
    List<Sprite> findAllByUserUserId(Integer userId);
    Sprite findByName(String name);
}