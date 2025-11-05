package com.gameover.hub.util;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Conexao{
	private static Connection uniqueInstance;

	private static Connection createInstance() {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USER");
        String senha = dotenv.get("DB_PASSWORD");

        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            log.error("Falha ao conectar no banco de dados. URL: [" + url + "], Usuário: [" + usuario + "]", e);
			throw new RuntimeException("Não foi possível estabelecer a conexão com o banco de dados", e);
        }
    }

	public static Connection getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = createInstance();
		}
		return uniqueInstance;
	}
}