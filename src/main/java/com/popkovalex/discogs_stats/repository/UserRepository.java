package com.popkovalex.discogs_stats.repository;

import com.popkovalex.discogs_stats.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);
}
