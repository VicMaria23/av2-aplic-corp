package br.com.aplcorp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dataNasc;

    @Column(nullable = false)
    private LocalDate dataCad;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private boolean isAdmin;

    // Construtor padrão exigido pelo JPA/Hibernate
    public Usuario() {
    }

    // Construtor personalizado
    public Usuario(String cpf, String nome, String senha, String email, LocalDate dataNasc, boolean ativo, boolean isAdmin) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNasc = dataNasc;
        this.ativo = ativo;
        this.isAdmin = isAdmin;
    }

    // getters e setters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public LocalDate getDataCad() {
        return dataCad;
    }
    public void setDataCad(LocalDate dataCad) {
        this.dataCad = dataCad;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(cpf, usuario.cpf) &&
               Objects.equals(nome, usuario.nome) &&
               Objects.equals(senha, usuario.senha) &&
               Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, nome, senha, email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", ativo='" + ativo + '\'' +
                ", isAdmin=" + isAdmin + '\'' +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        dataCad = LocalDate.now();
    }
}
