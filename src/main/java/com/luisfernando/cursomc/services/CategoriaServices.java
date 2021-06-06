package com.luisfernando.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisfernando.cursomc.domain.Categoria;
import com.luisfernando.cursomc.repositories.CategoriaRepository;
import com.luisfernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo " + Categoria.class.getName()));
		                         
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}
}
