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

    @NotBlank(message = "{title.not.blank}")
    @Size(min = 3, max = 15, message = "{title.size}")
    @Column(name = "title")
    private String title;

    @NotNull(message = "{amount.not.null}")
    @Column(name = "amount")
    private Double amount;

    @NotBlank(message = "{category.not.blank}")
    @Column(name = "category")
    private String category;

    @Column(name = "note")
    @Size(min = 2, max = 100, message = "{note.size}")
    private String note;

    @UpdateTimestamp
    @Column(name = "date")
    private Date date;

    public Finance() {
    }

}
