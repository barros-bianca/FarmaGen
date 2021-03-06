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

import com.generation.farmaGen.models.ProdutoModel;
import com.generation.farmaGen.repository.ProdutoRepository;




@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")


public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idProd}")
	public ResponseEntity <ProdutoModel> GetById (@PathVariable long idProd){
		return repository.findById(idProd)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<ProdutoModel>> GetByTitulo(@PathVariable String tituloProd){
		return ResponseEntity.ok(repository.findAllByTituloProdContainingIgnoreCase(tituloProd));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> post (@Valid @RequestBody ProdutoModel produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> put (@Valid @RequestBody ProdutoModel produtos){
		return ResponseEntity.status (HttpStatus.OK).body(repository.save(produtos));
	}
	
	@DeleteMapping("/{idProd}")
	public void delete (@PathVariable long idProd) {
		repository.deleteById(idProd);
	}
}
	

