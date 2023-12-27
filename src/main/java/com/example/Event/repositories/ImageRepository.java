package com.example.Event.repositories;

import com.example.Event.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
}
