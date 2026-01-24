package com.popkovalex.discogs_stats.repository;

import com.popkovalex.discogs_stats.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
