package com.fluffy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMANDS")
public class Command {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_seq")
    @SequenceGenerator(name = "command_seq", sequenceName = "COMMANDS_SEQ", allocationSize = 1)
    @Column(name = "COMMAND_ID")
    private Integer commandId;

    @Column(name = "COMMAND_NUMBER")
    private Integer commandNumber;



}
