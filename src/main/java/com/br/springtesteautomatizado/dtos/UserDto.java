package com.br.springtesteautomatizado.dtos;

import java.util.Objects;

public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private Integer age;

    public UserDto(String name, String cpf, Integer age) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) && Objects.equals(cpf, userDto.cpf) && Objects.equals(age, userDto.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, age);
    }
}
