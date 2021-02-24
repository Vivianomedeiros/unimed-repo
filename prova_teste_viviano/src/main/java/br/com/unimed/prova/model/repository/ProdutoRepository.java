package br.com.unimed.prova.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unimed.prova.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Query("SELECT p FROM Produto p WHERE UPPER(p.descricao) LIKE %:consultaDescricao%")
	public List<Produto> consultaPorNome(@Param("consultaDescricao") String descricao);

}
