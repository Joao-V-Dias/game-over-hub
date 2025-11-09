package com.gameover.hub.service;

import com.gameover.hub.dao.ClienteDAO;
import com.gameover.hub.model.Cliente;

import java.util.List;

public class ClienteService{
	private final ClienteDAO cDAO;

	public ClienteService(){
		this.cDAO = new ClienteDAO();
	}

	public Cliente salvar(Cliente c){
		return cDAO.salvar(c);
	}

	public List<Cliente> getClientes() {
		return cDAO.getClientes();
	}

	public Cliente getCliente(int id) {
		return cDAO.getCliente(id);
	}

	public Cliente getClientePorNome(String nome) {
		return cDAO.getClientePorNome(nome);
	}

	public Cliente editar(Cliente c) {
		return cDAO.editar(c);
	}

	public void deletar(int id) {
		cDAO.deletar(id);
	}

}