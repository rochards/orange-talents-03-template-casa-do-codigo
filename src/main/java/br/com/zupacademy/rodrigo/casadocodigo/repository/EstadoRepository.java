package br.com.zupacademy.rodrigo.casadocodigo.repository;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    Optional<Estado> findByNomeAndPaisId(String nome, Integer paisId);
    List<Estado> findByPaisId(Integer paisId);
}
