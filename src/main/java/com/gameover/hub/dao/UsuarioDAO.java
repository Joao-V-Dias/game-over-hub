package com.gameover.hub.dao;

import com.gameover.hub.model.Usuario;
import com.gameover.hub.util.Conexao;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UsuarioDAO{

    Connection conn;

    public UsuarioDAO() {
        conn = Conexao.getInstance();
    }

    private static final String CONSULTAR_USUARIO_NOME = "SELECT * FROM usuario WHERE email = ?";

    public Usuario getUsuario(String email) {
        Usuario usuario = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(CONSULTAR_USUARIO_NOME);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = mapperUsuario(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.error("Falha ao buscar usuário", e);
        }
        return usuario;
    }

    private Usuario mapperUsuario(ResultSet rs) throws SQLException {
        Usuario a = new Usuario();
        a.setId(rs.getInt("id"));
        a.setNome(rs.getString("nome"));
        a.setEmail(rs.getString("email"));
        a.setSenha(rs.getString("senha"));
        a.setTelefone(rs.getString("telefone"));
        return a;
    }
}
