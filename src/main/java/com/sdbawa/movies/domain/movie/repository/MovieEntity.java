package com.sdbawa.movies.domain.movie.repository;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author simar bawa
 */
@Data
@Entity
@Table(name = "MOVIE")
public class MovieEntity {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "incrOneSeq",
                    initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "total_budget")
    private Integer totalBudget;
}
