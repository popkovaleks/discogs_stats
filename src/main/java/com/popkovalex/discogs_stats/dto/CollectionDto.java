package com.popkovalex.discogs_stats.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollectionDto {

    private List<ReleaseResponseDto> releases;
}
