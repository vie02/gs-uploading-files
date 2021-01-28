package com.example.uploadingfiles.image;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Image {

    @Id
    @NonNull
    @GeneratedValue
    Long id;

    String name;

    String location;

    @Lob
    byte[] content;

}
