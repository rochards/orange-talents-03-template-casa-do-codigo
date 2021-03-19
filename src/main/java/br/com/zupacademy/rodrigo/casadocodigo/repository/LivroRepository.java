package br.com.zupacademy.rodrigo.casadocodigo.repository;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
