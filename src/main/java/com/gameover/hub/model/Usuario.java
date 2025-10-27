package com.gameover.hub.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario{

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

	public Usuario(){
	}

	public Usuario(String email, String senha){
		this.email = email;
		this.senha = senha;
	}
}
