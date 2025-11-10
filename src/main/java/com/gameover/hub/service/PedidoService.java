package com.gameover.hub.service;

import com.gameover.hub.dao.PedidoDAO;
import com.gameover.hub.model.ItensPedido;
import com.gameover.hub.model.Pedido;

public class PedidoService{
	private final PedidoDAO pedidoDAO;
	private final ItensPedidoService pedidoService;

	public PedidoService(){
		pedidoDAO = new PedidoDAO();
		pedidoService = new ItensPedidoService();
	}

	public void salvar(Pedido pedido){
		pedido = pedidoDAO.salvar(pedido);
		System.out.println(pedido.getId());
		if(pedido.getId() != 0){
			for(ItensPedido itensPedido : pedido.getItens()){
				itensPedido.setPedido(pedido);
				pedidoService.salvar(itensPedido);
			}
		}
	}

}