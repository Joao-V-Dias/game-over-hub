package com.gameover.hub.dao;

import com.gameover.hub.model.Cliente;
import com.gameover.hub.util.Conexao;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClienteDAO{
	private final Connection conn;

    public ClienteDAO() {
        this.conn = Conexao.getInstance();
    }

	private static final String CRIAR_CLIENTE =
		"INSERT INTO cliente (nome, telefone, endereco, cpf, email) VALUES (?, ?, ?, ?, ?)";

	private static final String BUSCAR_TODOS_CLIENTES =
		"SELECT id, nome, telefone, endereco, cpf, email FROM cliente";

	private static final String BUSCAR_CLIENTE_POR_ID =
		"SELECT id, nome, telefone, endereco, cpf, email FROM cliente WHERE id = ?";

	private static final String EDITAR_CLIENTE =
		"UPDATE cliente SET nome = ?, telefone = ?, endereco = ?, cpf = ?, email = ? WHERE id = ?";

	private static final String DELETAR_CLIENTE =
		"DELETE FROM cliente WHERE id = ?";

	private static final String BUSCAR_CLIENTE_POR_NOME =
		"SELECT id, nome, telefone, endereco, cpf, email FROM cliente WHERE nome ILIKE ?";

	public Cliente salvar(Cliente c){
		try {
            PreparedStatement stmt = conn.prepareStatement(CRIAR_CLIENTE, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getEndereco());
			stmt.setString(4, c.getCpf());
			stmt.setString(5, c.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.error("Falha ao salvar cliente: ", e);
        }
        return c;
	}

	public List<Cliente> getClientes() {
        List<Cliente> lstCliente = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_TODOS_CLIENTES);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstCliente.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar clientes: ", e);
        }
        return lstCliente;
    }

    public Cliente getCliente(int id) {
        Cliente c = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_CLIENTE_POR_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                c = mapearCliente(rs);
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar cliente: ", e);
        }
        return c;
    }

	public List<Cliente> getClientePorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_CLIENTE_POR_NOME);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar cliente por nome: {}", nome, e);
        }
        return clientes;
    }

    public Cliente editar(Cliente c) {
        try {
            PreparedStatement stmt = conn.prepareStatement(EDITAR_CLIENTE);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getEndereco());
			stmt.setString(4, c.getCpf());
			stmt.setString(5, c.getEmail());
			stmt.setInt(6, c.getId());
            stmt.executeUpdate();
            stmt.close();
            return c;
        } catch (SQLException e) {
            log.error("Falha ao editar cliente: ", e);
        }
        return null;
    }

	public void deletar(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(DELETAR_CLIENTE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            log.error("Falha ao deletar cliente: ", e);
        }
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException{
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
		c.setNome(rs.getString("nome"));
		c.setTelefone(rs.getString("telefone"));
		c.setEndereco(rs.getString("endereco"));
		c.setCpf(rs.getString("cpf"));
		c.setEmail(rs.getString("email"));
        return c;
    }
}
