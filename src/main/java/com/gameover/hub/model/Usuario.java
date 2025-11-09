package com.gameover.hub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usuario{

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

	public Usuario(String email, String senha){
		this.email = email;
		this.senha = senha;
	}
}
