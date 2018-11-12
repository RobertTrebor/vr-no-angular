package de.lengsfeld.apps.vr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cemetery")
public class Cemetery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CEMETERY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id")
    private List<Grave> graves;

    protected Cemetery() {
    }

    public Cemetery(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Grave> getGraves() {
        return graves;
    }

    public void setGraves(List<Grave> graves) {
        this.graves = graves;
    }
}
