package com.luisfernando.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisfernando.cursomc.domain.Cliente;
import com.luisfernando.cursomc.repositories.ClienteRepository;
import com.luisfernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo " + Cliente.class.getName()));
	}

}
