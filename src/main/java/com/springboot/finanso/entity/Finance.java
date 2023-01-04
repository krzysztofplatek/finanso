package com.springboot.finanso.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "finance")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "amount")
    private double amount;

    @NotBlank(message = "You should choose category")
    @Column(name = "category")
    private String category;

    @Column(name = "note")
    private String note;

    @UpdateTimestamp
    @Column(name = "date")
    private Date date;

    public Finance() {
    }
}
