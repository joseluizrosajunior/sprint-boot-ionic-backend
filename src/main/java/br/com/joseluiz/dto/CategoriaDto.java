package br.com.joseluiz.dto;

import br.com.joseluiz.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDto implements Serializable {

    private Integer id;
    @NotEmpty(message = "O campo nome deve ser preenchido")
    @Length(min = 5, max = 80, message = "O campo nome deve ter entre {min} e {max} caracteres")
    private String nome;

    public CategoriaDto() {
    }

    public CategoriaDto (Categoria c) {
        this.id = c.getId();
        this.nome = c.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
