package br.com.aplcorp.dto;

import java.time.LocalDate;

public class CarroListDTO {
    private long id;
    private String nome;
    private double preco;
    private int ano;
    private String marcas;
    private String cidade;
    private String placa;
    private LocalDate dataCad;

    // Construtor padrão necessário para o framework
    public CarroListDTO() {
    }

    // Construtor com todos os campos
    public CarroListDTO(long id, String nome, double preco, int ano, String marcas, String cidade, String placa, LocalDate dataCad) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ano = ano;
        this.marcas = marcas;
        this.cidade = cidade;
        this.placa = placa;
        this.dataCad = dataCad;
    }

    // Getters e Setters
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

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
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

    @Override
    public String toString() {
        return "CarroListDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                ", marcas='" + marcas + '\'' +
                ", cidade='" + cidade + '\'' +
                ", placa='" + placa + '\'' +
                ", dataCad=" + dataCad +
                '}';
    }
}
