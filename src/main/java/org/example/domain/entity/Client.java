package org.example.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Demmand> demmands;

    public Client(){
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonIgnore
    public Set<Demmand> getDemands() {
        return demmands;
    }

    public void setDemmand(Set<Demmand> demmands) {
        this.demmands = demmands;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", Nome = " + name+
                '}';
    }
}