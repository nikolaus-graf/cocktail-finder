package org.graf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Zutat", uniqueConstraints = {@UniqueConstraint(name = "uq_zutat_zutat", columnNames = {"name"})})
public class Zutat extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    public Zutat() {

    }

    public Zutat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Zutat [name=" + name + "]";
    }


}
