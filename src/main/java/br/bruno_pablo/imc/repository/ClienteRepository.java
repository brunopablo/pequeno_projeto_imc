package br.bruno_pablo.imc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bruno_pablo.imc.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    
}
