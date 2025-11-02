package com.gameover.hub.dao;

import com.gameover.hub.model.Produto;
import com.gameover.hub.util.Conexao;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProdutoDAO{

    Connection conn;

    public ProdutoDAO() {
        this.conn = new Conexao().conectar();
    }

	private static final String CRIAR_PRODUTO =
		"INSERT INTO produtos (nome, preco, categoria, quantidade, estoque_minimo, gtin) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String BUSCAR_TODOS_PRODUTOS =
		"SELECT id, nome, preco, categoria, quantidade, estoque_minimo, gtin FROM produtos";

	private static final String BUSCAR_PRODUTOS_POR_NOME =
		"SELECT id, nome, preco, categoria, quantidade, estoque_minimo, gtin FROM produtos WHERE nome ILIKE ?";

	private static final String BUSCAR_PRODUTO_POR_ID =
		"SELECT id, nome, preco, categoria, quantidade, estoque_minimo, gtin FROM produtos WHERE id = ?";

	private static final String EDITAR_PRODUTO =
		"UPDATE produtos SET nome = ?, preco = ?, categoria = ?, quantidade = ?, estoque_minimo = ?, gtin = ? WHERE id = ?";

    public Produto salvar(Produto p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(CRIAR_PRODUTO, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
			stmt.setBigDecimal(2, p.getPreco());
			stmt.setString(3, p.getCategoria());
			stmt.setInt(4, p.getQuantidade());
			stmt.setInt(5, p.getEstoqueMinimo());
			stmt.setString(6, p.getGtin());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.error("Falha ao salvar produto: ", e);
        }
        return p;
    }

    public List<Produto> getProduto() {
        List<Produto> lstProduto = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_TODOS_PRODUTOS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstProduto.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar produtos: ", e);
        }
        return lstProduto;
    }

    public List<Produto> getProduto(String nome) {
        List<Produto> lstProduto = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_PRODUTOS_POR_NOME);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstProduto.add(mapearProduto(rs));
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar produtos: ", e);
        }
        return lstProduto;
    }

    public Produto getProduto(int id) {
        Produto p = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(BUSCAR_PRODUTO_POR_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = mapearProduto(rs);
            }
        } catch (SQLException e) {
            log.error("Falha ao buscar produto: ", e);
        }
        return p;
    }

    public Produto editar(Produto p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(EDITAR_PRODUTO);
			stmt.setString(1, p.getNome());
			stmt.setBigDecimal(2, p.getPreco());
			stmt.setString(3, p.getCategoria());
			stmt.setInt(4, p.getQuantidade());
			stmt.setInt(5, p.getEstoqueMinimo());
			stmt.setString(6, p.getGtin());
			stmt.setInt(7, p.getId());
            stmt.executeQuery();
            stmt.close();
            return p;
        } catch (SQLException e) {
            log.error("Falha ao editar produto: ", e);
        }
        return null;
    }

    private Produto mapearProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
		p.setNome(rs.getString("nome"));
		p.setPreco(rs.getBigDecimal("preco"));
		p.setCategoria(rs.getString("categoria"));
		p.setQuantidade(rs.getInt("quantidade"));
		p.setEstoqueMinimo(rs.getInt("estoque_minimo"));
		p.setGtin(rs.getString("gtin"));
        return p;
    }
}
