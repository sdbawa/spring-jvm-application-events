package com.sdbawa.movies.domain.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author simar bawa
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}
