package br.com.aplcorp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private LocalDate dataCad;

    // Construtor padrão exigido pelo JPA/Hibernate
    public Carro() {
    }

    // Construtor personalizado
    public Carro(String nome, double preco, int ano, String marca, String cidade, String placa) {
        this.nome = nome;
        this.preco = preco;
        this.ano = ano;
        this.marca = marca;
        this.cidade = cidade;
        this.placa = placa;
    }

    // Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataCad() {
        return dataCad;
    }

    public void setDataCad(LocalDate dataCad) {
        this.dataCad = dataCad;
    }

    @PrePersist
    protected void onCreate() {
        dataCad = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carro carro = (Carro) obj;
        return Objects.equals(id, carro.id) &&
               Objects.equals(nome, carro.nome) &&
               Objects.equals(placa, carro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, placa);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                ", marca='" + marca + '\'' +
                ", cidade='" + cidade + '\'' +
                ", placa='" + placa + '\'' +
                ", dataCad=" + dataCad +
                '}';
    }
}
