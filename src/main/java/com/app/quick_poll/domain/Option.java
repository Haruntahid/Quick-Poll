package com.app.quick_poll.domain;

import jakarta.persistence.*;
import org.hibernate.boot.model.naming.Identifier;

@Entity
@Table(name="option_table")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="OPTION_VALUE")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
