package com.popkovalex.discogs_stats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReleaseResponseDto {
    private Long id;

    @JsonProperty("folder_id")
    private Long folderId;
    @JsonProperty("basic_information")
    private BasicReleaseInformation basicInformation;

    @Setter
    @Getter
    public static class BasicReleaseInformation {
        private Long id;
        private String title;
        private int year;

        @JsonProperty("cover_image")
        private String coverImage;
        private List<LabelsDto> labels;
        private List<ArtistDto> artists;
    }

    @Setter
    @Getter
    public static class LabelsDto {
        private String name;
    }



}

