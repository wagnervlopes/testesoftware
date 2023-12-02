package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private double preco;
    private String nome;

    public Produto(double preco, String nome) {
        this.preco = preco;
        this.nome = nome;
    }
}
