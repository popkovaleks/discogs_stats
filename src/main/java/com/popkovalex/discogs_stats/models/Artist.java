package com.popkovalex.discogs_stats.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long discogsId;

    @Column(nullable = false)
    private String name;


    @Override
    public int hashCode() {
        return Objects.hash(discogsId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Artist other = (Artist) obj;
        return Objects.equals(discogsId, other.discogsId);
    }
}
