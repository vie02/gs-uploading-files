package com.example.uploadingfiles.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ImageDbRepository extends JpaRepository<Image, Long> {

}
