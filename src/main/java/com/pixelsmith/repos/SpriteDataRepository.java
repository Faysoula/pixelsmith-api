package com.pixelsmith.repos;

import com.pixelsmith.Models.SpriteData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpriteDataRepository extends JpaRepository<SpriteData, Integer> {
}