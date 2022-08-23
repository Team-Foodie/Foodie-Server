package com.foodie.foodie.domain.post.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Embeddable
@Table(name = "post_content")
public class PostContent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    @Setter
    private Boolean isThumbnail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_idx")
    @JsonBackReference
    private Post post;

    @Builder
    public PostContent(Long idx, String type, String content, Boolean isThumbnail, Post post) {
        this.idx = idx;
        this.type = type;
        this.content = content;
        this.isThumbnail = isThumbnail;
        this.post = post;
    }
}
