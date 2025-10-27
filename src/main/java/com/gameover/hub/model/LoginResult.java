package com.gameover.hub.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult{
    private boolean sucesso;
    private String mensagem;
    private Usuario usuario;
}
