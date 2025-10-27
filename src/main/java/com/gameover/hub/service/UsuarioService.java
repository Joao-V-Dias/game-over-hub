package com.gameover.hub.service;

import com.gameover.hub.dao.UsuarioDAO;
import com.gameover.hub.model.LoginResult;
import com.gameover.hub.model.Usuario;

public class UsuarioService{

    UsuarioDAO uDAO;

    public UsuarioService() {
        uDAO = new UsuarioDAO();
    }

    public LoginResult validarLogin(Usuario usuario) {
		LoginResult result = new LoginResult();
        Usuario u = uDAO.getUsuario(usuario.getEmail());

        if (u == null || !u.getSenha().equals(usuario.getSenha())) {
            result.setSucesso(false);
            result.setMensagem("Usuário ou senha inválidos!");
            return result;
        }

        result.setSucesso(true);
        result.setMensagem("Login realizado com sucesso!");
        result.setUsuario(u);
        return result;
    }
}
