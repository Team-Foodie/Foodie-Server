package com.foodie.foodie.api.scrap.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScrapResponse {
    @JsonProperty("scrapItemList")
    private List<ScrapItem> scrapItemList = new ArrayList<>();
    @JsonProperty
    private String isAdded;

    public void from(List<ScrapItem> scrapItemList) {
        this.scrapItemList.addAll(scrapItemList);
    }
}
