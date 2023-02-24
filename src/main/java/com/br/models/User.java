package com.br.models;

public class User {
	
	private String nome;
	private String cpf;
	private Integer idade;
	
	public User(String nome, String cpf, Integer idade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
	}
	public User() {
		super();
		System.out.println("teste");
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
