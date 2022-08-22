package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Embeddable
@Table(name = "post_content")
public class PostContent extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private Boolean isThumbnail;

    @ManyToOne
    @JoinColumn(name = "post_idx")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    public PostContent(PostContent postContent) {
        BeanUtils.copyProperties(postContent, this);
    }

}
