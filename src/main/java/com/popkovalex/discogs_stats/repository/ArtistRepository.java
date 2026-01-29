package com.popkovalex.discogs_stats.repository;

import com.popkovalex.discogs_stats.models.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface ArtistRepository extends CrudRepository<Artist,Long> {
    Optional<Artist> findArtistByDiscogsId(Long discogsId);

    List<Artist> findAllByDiscogsIdIn(Set<Long> discogsIds);
}
