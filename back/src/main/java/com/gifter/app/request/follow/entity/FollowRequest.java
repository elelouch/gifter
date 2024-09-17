package com.gifter.app.request.follow.entity;

import com.gifter.app.user.entity.GifterUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class FollowRequest {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private GifterUser userOrigin;
    @ManyToOne
    private GifterUser userDestination;
    private boolean used;
    private Date date;
}
