package com.sdbawa.mmts.domain.director.repository;

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
@Table(name = "DIRECTOR")
public class DirectorEntity {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "incrOneSeq",
                    initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender_id")
    private Integer genderId;

    @Column(name = "status_id")
    private Integer statusId;
}
