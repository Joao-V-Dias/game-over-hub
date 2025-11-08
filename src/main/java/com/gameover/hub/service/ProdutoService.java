package com.gameover.hub.service;

import com.gameover.hub.dao.ProdutoDAO;
import com.gameover.hub.model.Produto;

import java.util.List;

public class ProdutoService{

    private final ProdutoDAO pDAO;

    public ProdutoService() {
        this.pDAO = new ProdutoDAO();
    }

    public Produto salvar(Produto p) {
        return pDAO.salvar(p);
    }

    public List<Produto> getProduto() {
        return pDAO.getProduto();
    }

    public List<Produto> getProduto(String nome) {
        return pDAO.getProduto(nome);
    }

    public Produto getProduto(int id) {
        return pDAO.getProduto(id);
    }

    public Produto editar(Produto p) {
        return pDAO.editar(p);
    }
}
