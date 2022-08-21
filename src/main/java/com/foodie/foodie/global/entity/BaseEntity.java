package com.foodie.foodie.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idx;

    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    public ResponseItem toResponseItem() {
        ResponseItem responseItem = new ResponseItem();
        responseItem.setPageId(idx.toString() + createdAt.toString());
        return responseItem;
    }

}
