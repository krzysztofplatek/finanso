package com.springboot.finanso.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;


@Entity
@Table(name = "finance")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "note")
    private String note;

    @UpdateTimestamp
    @Column(name = "date")
    private Date date;

    public Finance() {
    }

    public Finance(int id, String title, double amount, String category, String note, Date date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                '}';
    }
}
