-- liquibase formatted sql

-- changeset popkovalex:001-create-table-users
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'users';
CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       discogs_name VARCHAR(255),
                       role TINYINT NOT NULL,
                       CONSTRAINT pk_users PRIMARY KEY (id),
                       CONSTRAINT uq_users_email UNIQUE (email),
                       CONSTRAINT uq_users_discogs_name UNIQUE (discogs_name)
);

-- changeset popkovalex:002-create-table-releases
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'releases';
CREATE TABLE releases (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         discogs_id BIGINT UNIQUE,
                         title VARCHAR(255) NOT NULL,
                         year INT NOT NULL,
                         CONSTRAINT pk_release PRIMARY KEY (id)
);

-- changeset popkovalex:003-create-table-artists
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'artists';
CREATE TABLE artists (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         discogs_id BIGINT UNIQUE,
                         name VARCHAR(255) NOT NULL,
                         CONSTRAINT pk_artists PRIMARY KEY (id)
);

-- changeset popkovalex:004-create-table-artist_release
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_name = 'artist_release';
CREATE TABLE artist_release (
                                release_id BIGINT NOT NULL,
                                artist_id BIGINT NOT NULL,
                                CONSTRAINT pk_artist_release PRIMARY KEY (release_id, artist_id),
                                CONSTRAINT fk_artist_release_release
                                    FOREIGN KEY (release_id) REFERENCES releases (id)
                                        ON DELETE CASCADE,
                                CONSTRAINT fk_artist_release_artist
                                    FOREIGN KEY (artist_id) REFERENCES artists (id)
                                        ON DELETE CASCADE
);

-- Индексы по внешним ключам в таблице связей many-to-many
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.statistics WHERE table_name = 'artist_release' AND index_name = 'idx_artist_release_release_id';
CREATE INDEX idx_artist_release_release_id ON artist_release (release_id);
-- preconditions onFail: MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.statistics WHERE table_name = 'artist_release' AND index_name = 'idx_artist_release_artist_id';
CREATE INDEX idx_artist_release_artist_id ON artist_release (artist_id);

-- Индексы по discogs_id (уникальные идентификаторы из внешней системы)
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.statistics WHERE table_name = 'releases' AND index_name = 'idx_releases_discogs_id';
CREATE INDEX idx_releases_discogs_id ON releases (discogs_id);
-- preconditions onFail:MARK_RAN
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.statistics WHERE table_name = 'artists' AND index_name = 'idx_artists_discogs_id';
CREATE INDEX idx_artists_discogs_id ON artists (discogs_id);

-- rollback DROP INDEX idx_artists_discogs_id ON artists;
-- rollback DROP INDEX idx_release_discogs_id ON release;
-- rollback DROP INDEX idx_artist_release_artist_id ON artist_release;
-- rollback DROP INDEX idx_artist_release_release_id ON artist_release;
-- rollback DROP TABLE artist_release;
-- rollback DROP TABLE artists;
-- rollback DROP TABLE release;
-- rollback DROP TABLE users;

