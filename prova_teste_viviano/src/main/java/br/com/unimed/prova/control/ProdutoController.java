package br.com.unimed.prova.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.unimed.prova.dto.ProdutoDTO;
import br.com.unimed.prova.model.entity.Produto;
import br.com.unimed.prova.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto-api")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@RequestBody Produto produto) {
		produto.setDataCadastro(new Date());
		return repository.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
		
		Produto produto = repository.findById(id).get();		
		produto.setDescricao(produtoAtualizado.getDescricao());
			
		return repository.save(produto);
	}
	
	@GetMapping("/lista-todos")
	@ResponseStatus(HttpStatus.FOUND)
	public List<ProdutoDTO> listarTodos(){
		List<Produto> produtos = repository.findAll();
		List<ProdutoDTO> lst = new ArrayList<>();
		produtos.forEach(a -> {
			ProdutoDTO dto = new ProdutoDTO();
			dto.setId(a.getId());
			dto.setDescricao(a.getDescricao());
			dto.setDataCadastro(a.getDataCadastro().toString());
			lst.add(dto);
		});
		
		return lst;
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public Produto consultarPorId(@PathVariable Integer id) {
		return repository.findById(id).get();
	}
	
	@GetMapping("/consulta-por-descricao/{descricao}")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Produto> consultarPorNome(@PathVariable String descricao){
		descricao = descricao.toUpperCase();
		return repository.consultaPorNome(descricao);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void remover(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/data-sistema")
	@ResponseStatus(HttpStatus.FOUND)
	public Date dataSistema() {
		return new Date();
	}
	
	/*
	 * TODO FALTA REMOVER / LISTAR
	 * AJEITAR A CONEXÃO 
	 * 
	 */
}
