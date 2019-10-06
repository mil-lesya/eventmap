package com.gmail.mileshko.lesya.eventmap.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Marker marker;

    public Event() {
    }

    public Event(Timestamp date, String description, Integer price, String name, Marker marker) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.name = name;
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
