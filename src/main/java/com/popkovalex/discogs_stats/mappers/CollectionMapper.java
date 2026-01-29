package com.popkovalex.discogs_stats.mappers;


import com.popkovalex.discogs_stats.dto.ArtistDto;
import com.popkovalex.discogs_stats.dto.CollectionDto;
import com.popkovalex.discogs_stats.dto.ReleaseResponseDto;
import com.popkovalex.discogs_stats.dto.ResponseFromDiscogsDto;
import com.popkovalex.discogs_stats.models.Artist;
import com.popkovalex.discogs_stats.models.Release;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    CollectionDto collectionToDto(ResponseFromDiscogsDto responseFromDiscogsDto);
    List<Release> collectionToEntity(List<ReleaseResponseDto> releasesResponseDto);

    @Mapping(source = "id", target = "discogsId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "basicInformation.title", target = "title")
    @Mapping(source = "basicInformation.year", target = "year")
    @Mapping(source = "basicInformation.artists", target = "artist")
    Release releaseToEntity(ReleaseResponseDto releaseResponseDto);

    @Mapping(source = "id", target = "discogsId")
    @Mapping(target = "id", ignore = true)
    Artist artistToEntity(ArtistDto artistDto);

}
