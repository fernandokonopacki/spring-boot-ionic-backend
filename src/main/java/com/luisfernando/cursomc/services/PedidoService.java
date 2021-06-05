package com.luisfernando.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisfernando.cursomc.domain.Cliente;
import com.luisfernando.cursomc.domain.Pedido;
import com.luisfernando.cursomc.repositories.PedidoRepository;
import com.luisfernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo " + Cliente.class.getName()));
	}

}
