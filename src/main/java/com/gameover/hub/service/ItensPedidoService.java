package com.gameover.hub.service;

import com.gameover.hub.dao.ItensPedidoDAO;
import com.gameover.hub.model.ItensPedido;

public class ItensPedidoService{
	 private final ItensPedidoDAO itensPedidoDAO;

	 public ItensPedidoService(){
	 	itensPedidoDAO = new ItensPedidoDAO();
	 }

	 public void salvar(ItensPedido itensPedido){
	 	itensPedidoDAO.salvar(itensPedido);
	 }

}