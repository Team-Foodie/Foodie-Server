package com.foodie.foodie.api.upload.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class UploadResponse {
    @JsonProperty
    private List<UrlInfo> urlList = new ArrayList<>();

    public void addUrlToList(String url, boolean isThumbnail) {
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setUrl(url);
        if (isThumbnail) {
            urlInfo.setIsThumbnail(Boolean.TRUE.toString());
        }
        urlList.add(urlInfo);
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UrlInfo {
        private String url;
        private String isThumbnail;
    }
}
