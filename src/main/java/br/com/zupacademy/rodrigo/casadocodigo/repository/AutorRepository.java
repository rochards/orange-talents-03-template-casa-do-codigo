package br.com.zupacademy.rodrigo.casadocodigo.repository;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
