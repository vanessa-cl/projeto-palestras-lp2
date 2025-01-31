package main.java.entities;

import main.java.enums.CargoEnum;

public class Ouvinte {
    private String nome;
    private String email;
    private String senha;
    private CargoEnum cargo;

    public Ouvinte(String nome, String email, String senha, CargoEnum cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public String toString() {
        return "Ouvinte: " + nome + " - Email: " + email + " - Cargo: " + cargo + " - Senha: " + senha;
    }
}
