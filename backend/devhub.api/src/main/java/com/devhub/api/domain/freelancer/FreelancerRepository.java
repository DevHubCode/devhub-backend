package com.devhub.api.domain.freelancer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    Page<Freelancer> findAllByAtivoTrue(Pageable paginacao);
    Freelancer findByEmailAndSenha(String email, String senha);
}
