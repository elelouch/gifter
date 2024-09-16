package com.gifter.app.gift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Gift {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    @NotNull
    private String name;
    private String location;
}
