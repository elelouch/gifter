package com.gifter.app.gift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Gift {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String name;
    private String location;
}
