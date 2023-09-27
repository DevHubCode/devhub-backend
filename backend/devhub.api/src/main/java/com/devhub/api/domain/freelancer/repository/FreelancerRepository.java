package com.devhub.api.domain.freelancer.repository;

import com.devhub.api.domain.freelancer.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {
    Page<Freelancer> findAllByAtivoTrue(Pageable paginacao);
}
