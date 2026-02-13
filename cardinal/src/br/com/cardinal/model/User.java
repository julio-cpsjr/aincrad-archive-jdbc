package br.com.cardinal.model;

public class User {
    private int id;
    private String nome;
    private String email;
    private StatusUser status;

    public  User(int id, String nome, String email, StatusUser status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.status = status;
    }

    public User(String nome, String email, StatusUser status) {
        this.nome = nome;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }
}
