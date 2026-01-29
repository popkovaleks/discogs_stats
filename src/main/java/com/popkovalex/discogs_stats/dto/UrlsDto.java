package com.popkovalex.discogs_stats.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlsDto {
    private String first;
    private String last;
    private String next;
    private String prev;
}