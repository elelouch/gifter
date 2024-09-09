package com.gifter.app.request.follow.entity;

import com.gifter.app.user.entity.GifterUser;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class FollowRequest {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private GifterUser userOrigin;
    @OneToOne
    private GifterUser userDestination;
    private Date date;
}
