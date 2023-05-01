package com.demo.todoapp.domain;

import com.demo.todoapp.util.ToDoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo{

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(length = 256)
    private String name;

    @Column(length = 256)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ToDoStatus status;

    @Column(length = 256)
    private Date startDate;

    @Column(length = 256)
    private Date dueDate;

    @Column(length = 256)
    private String createdBy;

    @Column(length = 256)
    private String updatedBy;

}
