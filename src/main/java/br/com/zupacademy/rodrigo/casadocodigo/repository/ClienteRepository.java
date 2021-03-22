package br.com.zupacademy.rodrigo.casadocodigo.repository;

import br.com.zupacademy.rodrigo.casadocodigo.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
