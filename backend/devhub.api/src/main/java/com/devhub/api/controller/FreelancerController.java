package com.devhub.api.controller;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.domain.especialidade.EspecialidadeData;
import com.devhub.api.domain.especialidade.EspecialidadeRepository;
import com.devhub.api.domain.freelancer.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/freelancers")
public class FreelancerController {

    @Autowired
    private FreelancerRepository repository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createFreelancer(@Valid @RequestBody CreateFreelancerData data, UriComponentsBuilder uriBuilder) {

        var freelancer = new Freelancer(data);

        repository.save(freelancer);

        var listaEspecialidades = data.especialidades();

        for (EspecialidadeData dataEspec : listaEspecialidades) {
            var especialidade = new Especialidade(dataEspec, freelancer);
            especialidadeRepository.save(especialidade);
        }

        var uri = uriBuilder.path("/freelancers/{id}").buildAndExpand(freelancer.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailFreelancerData(freelancer));
    }

    @GetMapping
    public ResponseEntity<Page<ListFreelancerData>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListFreelancerData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListFreelancerData> listarFreelancerById(@PathVariable Long id) {
        var freelancer = repository.getReferenceById(id);
        return ResponseEntity.ok(new ListFreelancerData(freelancer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@Valid @RequestBody UpdateFreelancerData data) {
        var freelancer = repository.getReferenceById(data.id_freelancer());
        freelancer.atuallizarInformacoes(data);

        return ResponseEntity.ok(new DetailFreelancerData(freelancer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var freelancer = repository.getReferenceById(id);
        freelancer.excluir();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity ativarConta(@PathVariable Long id) {
        var freelancer = repository.getReferenceById(id);
        freelancer.ativarConta();
        return ResponseEntity.noContent().build();
    }

}
