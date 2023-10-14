package com.devhub.api.domain.contratante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    Page<Contratante> findAllByAtivoTrue(Pageable paginacao);
}
