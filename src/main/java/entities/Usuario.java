package main.java.entities;

import main.java.enums.CargoEnum;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private CargoEnum cargo;

    public Usuario(int id, String nome, String email, String senha, CargoEnum cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
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
        return "ID: " + id +
                " - Nome: " + nome +
                " - Email: " + email +
                " - Senha: " + senha +
                " - Cargo: " + cargo;
    }
}
