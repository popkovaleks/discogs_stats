package com.popkovalex.discogs_stats.repository;

import com.popkovalex.discogs_stats.models.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist,Long> {
}
