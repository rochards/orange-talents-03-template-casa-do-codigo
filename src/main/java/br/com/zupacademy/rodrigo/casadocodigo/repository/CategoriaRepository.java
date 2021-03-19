package br.com.zupacademy.rodrigo.casadocodigo.repository;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNome(String nome);
}
