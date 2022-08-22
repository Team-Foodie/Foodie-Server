package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
public class PostAttachmentFile {
    private String url;
    private PostJpo post;
    private Boolean isThumbnail;
}
