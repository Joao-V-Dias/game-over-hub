package com.gameover.hub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente{
	private int id;
	private String nome;
	private String telefone;
	private String endereco;
	private String cpf;
	private String email;
}