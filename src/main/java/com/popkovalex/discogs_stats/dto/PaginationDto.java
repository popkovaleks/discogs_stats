package com.popkovalex.discogs_stats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationDto {
    private int page;
    private int pages;
    private int items;

    @JsonProperty("per_page")
    private int perPage;
    private UrlsDto urls;
}