package com.gameover.hub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pedido{
	private int id;
	private Cliente cliente;
	private Date data;
	private List<ItensPedido> itens;
	private String garatia;
}