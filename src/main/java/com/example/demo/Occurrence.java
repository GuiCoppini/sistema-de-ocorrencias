package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Occurrence {
    @Id
    @GeneratedValue
    private Long Id;

    private Integer danger;

    private String description;

    public Long getId() {
        return Id;
    }

    public Integer getDanger() {

        return danger;
    }

    public Occurrence setDanger(Integer danger) {
        this.danger = danger;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Occurrence setDescription(String description) {
        this.description = description;
        return this;
    }
}
