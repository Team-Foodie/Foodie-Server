package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_attachment_file")
public class PostAttachmentFile extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String fileAttachmentUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Post post;

    private Boolean isThumbnail;

    @Builder
    public PostAttachmentFile(String fileAttachmentUrl, Post post, Boolean isThumbnail) {
        this.fileAttachmentUrl = fileAttachmentUrl;
        this.post = post;
        this.isThumbnail = isThumbnail;
    }
}
