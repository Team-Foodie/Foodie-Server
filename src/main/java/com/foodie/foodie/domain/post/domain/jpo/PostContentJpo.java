package com.foodie.foodie.domain.post.domain.jpo;

import com.foodie.foodie.domain.post.domain.PostContent;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_content")
public class PostContentJpo extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_idx", nullable = false)
    private PostJpo postJpo;

    @Column
    private Boolean isThumbnail;

    public PostContentJpo(PostContent postContent) {
        BeanUtils.copyProperties(postContent, this);
    }

    public PostContent toDomain() {
        PostContent postContent = new PostContent();
        BeanUtils.copyProperties(this, postContent);
        return postContent;
    }
}
