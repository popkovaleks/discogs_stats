package com.popkovalex.discogs_stats.services;

import com.popkovalex.discogs_stats.client.DiscogsClient;
import com.popkovalex.discogs_stats.dto.ArtistDto;
import com.popkovalex.discogs_stats.dto.CollectionDto;
import com.popkovalex.discogs_stats.dto.ReleaseResponseDto;
import com.popkovalex.discogs_stats.dto.ResponseFromDiscogsDto;
import com.popkovalex.discogs_stats.mappers.CollectionMapper;
import com.popkovalex.discogs_stats.models.Artist;
import com.popkovalex.discogs_stats.models.Release;
import com.popkovalex.discogs_stats.repository.ArtistRepository;
import com.popkovalex.discogs_stats.repository.ReleaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    @Autowired
    private DiscogsClient discogsClient;

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public CollectionDto getCollection(String discogsUsername, int folderId) {
        ResponseFromDiscogsDto response = discogsClient.getCollectionByUsername(discogsUsername, folderId);
        saveCollection(response.getReleases());
        CollectionDto collectionDto = collectionMapper.collectionToDto(response);
        return collectionDto;
    }

    @Transactional
    public void saveCollection(List<ReleaseResponseDto> releasesDto) {
        List<Release> releases = collectionMapper.collectionToEntity(releasesDto);

        Set<Artist> localArtistsCache = new HashSet<>();

        Map<Long, Artist> artistImportCache = releases.stream()
                .flatMap(release -> release.getArtist().stream())
                .collect(Collectors.toMap(
                        Artist::getDiscogsId,
                        Function.identity(),
                        (a, b) -> a
                ));

        Map<Long, Artist> artistFromDb = artistRepository.findAllByDiscogsIdIn(artistImportCache.keySet())
                .stream().collect(
                        Collectors.toMap(
                                Artist::getDiscogsId,
                                Function.identity()
                        )
                );

        List<Artist> newArtists = artistImportCache.entrySet().stream()
                .filter(e -> !artistFromDb.containsKey(e.getKey()))
                .map(e -> e.getValue())
                .collect(Collectors.toList());

        artistRepository.saveAll(newArtists);

        Map<Long, Artist> managedArtist = new HashMap<>(artistFromDb);
        newArtists.forEach(artist -> managedArtist.put(artist.getDiscogsId(), artist));

        for (Release release : releases) {
            Set<Artist> updatedReleaseArtist = release.getArtist().stream()
                    .map(artistFromRelease -> managedArtist.get(artistFromRelease.getDiscogsId()))
                    .collect(Collectors.toSet());

            release.setArtist(updatedReleaseArtist);
        }

        releaseRepository.saveAll(releases);
    }
}
