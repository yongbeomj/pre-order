package com.shop.activityservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "follows")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUserId;

    private Long toUserId;

    @Builder
    public Follow(Long fromUserId, Long toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

}
