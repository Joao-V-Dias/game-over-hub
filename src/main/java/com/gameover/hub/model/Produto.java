package com.gameover.hub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Produto{

    private int id;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private int quantidade;
	private String gtin;
	private int estoqueMinimo;

	public Produto(int estoqueMinimo, String gtin, int quantidade, String categoria, BigDecimal preco, String nome){
		this.estoqueMinimo = estoqueMinimo;
		this.gtin = gtin;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.preco = preco;
		this.nome = nome;
	}

	@Override
	public String toString(){
		return this.nome;
	}
}
