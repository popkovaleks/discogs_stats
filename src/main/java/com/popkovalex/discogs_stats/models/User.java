package com.popkovalex.discogs_stats.models;

import com.popkovalex.discogs_stats.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",  allocationSize = 1, sequenceName = "user_id_seq")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String discogsName;

    private Role role;
}
