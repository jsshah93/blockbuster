drop table if exists blockbuster CASCADE ;

CREATE TABLE blockbuster (
    id BIGINT AUTO_INCREMENT,
    movie_name VARCHAR(255),
    movie_rating INTEGER NOT NULL,
    movie_rel_date VARCHAR(255),
    PRIMARY KEY (id)
);