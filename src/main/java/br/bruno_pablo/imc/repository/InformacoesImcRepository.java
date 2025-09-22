package br.bruno_pablo.imc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bruno_pablo.imc.entity.InformacoesImc;

@Repository
public interface InformacoesImcRepository extends JpaRepository<InformacoesImc, Integer>{

}
