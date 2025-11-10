package com.gameover.hub.dao;

import com.gameover.hub.model.Pedido;
import com.gameover.hub.util.Conexao;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class PedidoDAO{
	private final Connection conn;

	public PedidoDAO(){
		conn = Conexao.getInstance();
	}

	private static final String CRIAR_PEDIDO =
			"INSERT INTO pedidos (id_cliente, data_pedido) VALUES (?, ?);";

	public Pedido salvar(Pedido pedido){
		 try {
		 	PreparedStatement stmt = conn.prepareStatement(CRIAR_PEDIDO, Statement.RETURN_GENERATED_KEYS);
		 	stmt.setInt(1, pedido.getCliente().getId());
		 	stmt.setDate(2, pedido.getData());
		 	stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt("id_pedido"));
            }
			stmt.close();
			rs.close();
		 } catch (SQLException e) {
		 	log.error("Falha ao salvar pedido: ", e);
		 }
		 return pedido;
	}

}