package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ViewScoped
@ManagedBean
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer autorId;
	private Livro livro = new Livro();
	private Integer livroId;

	public Livro getLivro() {
		return livro;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public void gravarAutor(){
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Libro escrito por.:" + autor.getNome());
	}
	
	public void removerAutorDoLivro(Autor autor){
		this.livro.removeAutor(autor);
	}

	public void gravar(){
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		
		if(this.livro.getId() == null){
			System.out.println("Novo livro.;" + this.livro.getId());
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			System.out.println("Atualiza livro.;" + this.livro.getId());
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro){
		System.out.println("Removendo livro " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void carregar(Livro livro){
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = livro;
	}
	
	private void carregaPelaId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);

	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
			
	}
}
