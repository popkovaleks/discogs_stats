package com.popkovalex.discogs_stats.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Release")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "release_id_seq")
    @SequenceGenerator(name = "realease_id_seq", sequenceName = "release_id_seq", allocationSize = 1)
    private Long id;

    private Long discogsId;

    private String title;

    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
