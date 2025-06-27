package com.example.AppPublico.models;

public class Evento {
    private Integer id;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFinal;
    private LocalEvento localEvento;
    private CategoriaEvento categoria;
    private OrganizadorEvento organizador;
    private String statusEvento;
    private String imagemNome;

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFinal() { return dataFinal; }
    public void setDataFinal(String dataFinal) { this.dataFinal = dataFinal; }

    public LocalEvento getLocalEvento() { return localEvento; }
    public void setLocalEvento(LocalEvento localEvento) { this.localEvento = localEvento; }

    public CategoriaEvento getCategoria() { return categoria; }
    public void setCategoria(CategoriaEvento categoria) { this.categoria = categoria; }

    public OrganizadorEvento getOrganizador() { return organizador; }
    public void setOrganizador(OrganizadorEvento organizador) { this.organizador = organizador; }

    public String getStatusEvento() { return statusEvento; }
    public void setStatusEvento(String statusEvento) { this.statusEvento = statusEvento; }

    public String getImagemNome() { return imagemNome; }
    public void setImagemNome(String imagemNome) { this.imagemNome = imagemNome; }
}
