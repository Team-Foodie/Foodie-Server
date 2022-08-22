package com.foodie.foodie.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class RestResponseData<T> {

    @JsonInclude(NON_NULL)
    private String resultCode;

    @JsonInclude(NON_NULL)
    private String message;

    /**
     * response time
     */
    private Date timestamp;


    /**
     * Any object that can be <strong>serialized</strong>!
     */
    @JsonInclude(NON_NULL)
    private T data;

    public RestResponseData(ResultCode resultCode) {
        this.resultCode = resultCode.name();
    }

    public RestResponseData(ResultCode resultCode, T data) {
        this.resultCode = resultCode.name();
        this.data = data;
    }

    public RestResponseData(ResultCode resultCode, String message, T data) {
        this.resultCode = resultCode.name();
        this.message = message;
        this.data = data;
    }

    public RestResponseData(T data) {
        this.data = data;
    }

    public ResponseEntity<RestResponseData<T>> buildResponseEntity(HttpStatus status) {
        this.timestamp = new Date(System.currentTimeMillis());
        return new ResponseEntity<>(this, status);
    }
}
