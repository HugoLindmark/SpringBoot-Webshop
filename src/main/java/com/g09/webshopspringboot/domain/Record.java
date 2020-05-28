package com.g09.webshopspringboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artist;
    private String title;
    private String imgURL;
    private int price;

    public Record(String artist, String title, String imgURL, int price) {
        this.artist = artist;
        this.title = title;
        this.imgURL = imgURL;
        this.price = price;
    }

}

