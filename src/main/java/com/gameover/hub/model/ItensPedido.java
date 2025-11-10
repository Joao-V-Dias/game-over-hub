package com.gameover.hub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItensPedido{
	private int id;
	private Pedido pedido;
	private Produto produto;
	private int quantidade;
	private BigDecimal precoUnitario;
}