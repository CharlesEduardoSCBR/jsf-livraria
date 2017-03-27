package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao {

	EntityManager em;
	private DAO<Livro> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(Livro.class, this.em);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
}
