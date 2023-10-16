package com.devhub.api.domain.contratante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    Page<Contratante> findAllByAtivoTrue(Pageable paginacao);
    Contratante findByEmailAndSenha(String email, String senha);
}
