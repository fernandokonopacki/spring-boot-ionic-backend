package com.luisfernando.cursomc.services;

import java.util.Date;
import java.util.Optional;

import com.luisfernando.cursomc.domain.*;
import com.luisfernando.cursomc.domain.enums.EstadoPagamento;
import com.luisfernando.cursomc.repositories.ItemPedidoRepository;
import com.luisfernando.cursomc.repositories.PagamentoRepository;
import com.luisfernando.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisfernando.cursomc.repositories.PedidoRepository;
import com.luisfernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private BoletoServices boletoServices;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteServices clienteServices;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo " + Cliente.class.getName()));
	}

	public Pedido insert(Pedido pedido){
		pedido.setId(null);
		pedido.setInstate(new Date());
		pedido.setCliente(clienteServices.buscar(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto){
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoServices.preencherPagamentoComBoleto(pagto, pedido.getInstate());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido ip : pedido.getItens()){
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		System.out.println(pedido);
		return pedido;
	}


}
