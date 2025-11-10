package com.gameover.hub.dao;

import com.gameover.hub.model.ItensPedido;
import com.gameover.hub.util.Conexao;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ItensPedidoDAO{

	private final Connection conn;

	public ItensPedidoDAO(){
		conn = Conexao.getInstance();
	}

	private static final String CRIAR_ITEM_PEDIDO =
			"INSERT INTO itenspedido (id_pedido, id_item, quantidade, valor_unitario) VALUES (?, ?, ?, ?);";

	public ItensPedido salvar(ItensPedido itensPedido){
		try {
			PreparedStatement stmt = conn.prepareStatement(CRIAR_ITEM_PEDIDO, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, itensPedido.getPedido().getId());
			stmt.setInt(2, itensPedido.getProduto().getId());
			stmt.setInt(3, itensPedido.getQuantidade());
			stmt.setBigDecimal(4, itensPedido.getPrecoUnitario());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				itensPedido.setId(rs.getInt(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			log.error("Falha ao salvar item do pedido: ", e);
		}
		return itensPedido;
	}

}