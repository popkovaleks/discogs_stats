package com.popkovalex.discogs_stats.repository;

import com.popkovalex.discogs_stats.models.Release;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release, Long> {
}
