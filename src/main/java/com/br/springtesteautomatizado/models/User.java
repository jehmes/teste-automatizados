package com.br.springtesteautomatizado.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "GP_USER")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable =false)
    private Integer age;

    public User() {
    }

    public User(String nome, String cpf, Integer idade) {
        this.name = nome;
        this.cpf = cpf;
        this.age = idade;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer idade) {
        this.age = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(cpf, user.cpf) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, age);
    }

    @Override
    public String toString() {
        return "User [nome=" + name + ", cpf=" + cpf + ", idade=" + age + "]";
    }

}
