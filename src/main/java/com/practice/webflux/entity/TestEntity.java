package com.practice.webflux.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("test_entity")
public class TestEntity {

    @Id
    private String ocid;

    @CreatedDate
    private LocalDateTime localDateTime;

    private String characterName;

    private String worldName;

    private String characterGender;

    private String characterClass;

    private String characterClassLevel;

    private int characterLevel;

    private int characterExp;

    private String characterExpRate;

    private String characterGuildName;

    private String characterImage;
}
