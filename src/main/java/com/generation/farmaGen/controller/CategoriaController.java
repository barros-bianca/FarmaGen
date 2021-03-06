package com.generation.farmaGen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmaGen.models.CategoriaModel;
import com.generation.farmaGen.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RequestMapping("/categorias")

public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<CategoriaModel>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idCat}")
	public ResponseEntity <CategoriaModel> GetById (@PathVariable long idCat){
		return repository.findById(idCat)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoriaCat/{categoriaCat}")
	public ResponseEntity<List<CategoriaModel>> GetByName(@PathVariable String categoriaCat){
		return ResponseEntity.ok(repository.findAllByDescricaoCatContainingIgnoreCase(categoriaCat));
	}

	@PostMapping
	public ResponseEntity<CategoriaModel> post (@Valid @RequestBody CategoriaModel categoriaCat){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoriaCat));
	}
	
	@PutMapping
	public ResponseEntity<CategoriaModel> put (@Valid @RequestBody CategoriaModel categoriaCat){
		return ResponseEntity.status (HttpStatus.OK).body(repository.save(categoriaCat));
	}
	
	@DeleteMapping("/{idCat}")
	public void delete (@PathVariable long idCat) {
		repository.deleteById(idCat);
	}

	public CategoriaRepository getRepository() {
		return repository;
	}

	public void setRepository(CategoriaRepository repository) {
		this.repository = repository;
	}






}
	
	

