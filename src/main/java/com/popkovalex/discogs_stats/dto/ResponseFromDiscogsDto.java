package com.popkovalex.discogs_stats.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseFromDiscogsDto {

    private PaginationDto pagination;
    private List<ReleaseResponseDto> releases;

    @Getter
    public class PaginationDto {
        private int page;
        private int pages;
        private int items;
        private int perPage;
        private UrlsDto urls;
    }

    @Getter
    public class UrlsDto {
        private String first;
        private String last;
        private String next;
        private String prev;
    }

    @Getter
    public class ReleaseResponseDto {
        private Long id;
        private Long folderId;
        private BasicReleaseInformation basicInformation;
    }

    @Getter
    public class BasicReleaseInformation {
        private Long id;
        private String title;
        private int year;
        private String coverImage;
        private List<LabelsDto> labels;
        private List<ArtistsDto> artists;
    }

    @Getter
    public class LabelsDto {
        private String name;
    }

    @Getter
    public class ArtistsDto {
        private String name;
    }
}
