package br.com.joseluiz.services;

import java.util.List;
import java.util.Optional;

import br.com.joseluiz.dto.CategoriaDto;
import br.com.joseluiz.services.exceptions.DateIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.joseluiz.domain.Categoria;
import br.com.joseluiz.repositories.CategoriaRepository;
import br.com.joseluiz.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findPage(Integer offset, Integer limit, String order, String direction) {
		PageRequest pageRequest = PageRequest.of(offset, limit, Sort.Direction.valueOf(direction), order);
		return repository.findAll(pageRequest);
	}

	public Categoria fromDto(CategoriaDto dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
}
