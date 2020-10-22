package com.sdbawa.mmts.domain.events.repository;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author simar bawa
 */
@Data
@Entity
@Table(name = "MOVIE_BUDGET_EVENTS")
public class MovieBudgetEventsEntity {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "incrOneSeq",
                    initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "event")
    private String event;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
