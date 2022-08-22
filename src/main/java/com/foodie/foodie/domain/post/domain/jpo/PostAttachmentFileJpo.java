package com.foodie.foodie.domain.post.domain.jpo;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.PostAttachmentFile;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_attachment_file")
public class PostAttachmentFileJpo extends BaseEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_idx", nullable = false)
    private PostJpo post;

    @Column
    private Boolean isThumbnail;

    public PostAttachmentFileJpo(PostAttachmentFile postAttachmentFile) {
        BeanUtils.copyProperties(postAttachmentFile, this);
    }

    public PostAttachmentFile toDomain() {
        PostAttachmentFile postAttachmentFile = new PostAttachmentFile();
        BeanUtils.copyProperties(this, postAttachmentFile);
        return postAttachmentFile;
    }
}
