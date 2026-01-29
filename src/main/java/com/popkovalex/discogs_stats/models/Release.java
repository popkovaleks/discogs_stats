package com.popkovalex.discogs_stats.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "releases")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long discogsId;

    @Column(nullable = false)
    private String title;

    private int year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_release",
    joinColumns = @JoinColumn(name = "release_id"),
    inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artist;
}
