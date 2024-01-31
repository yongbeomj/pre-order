package com.shop.preorder.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "posts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @Builder
    public Post(String title, String contents, User writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
