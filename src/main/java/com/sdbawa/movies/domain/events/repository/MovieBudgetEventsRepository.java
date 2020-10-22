package com.sdbawa.movies.domain.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author simar bawa
 */
public interface MovieBudgetEventsRepository extends JpaRepository<MovieBudgetEventsEntity, Integer>  {
}
