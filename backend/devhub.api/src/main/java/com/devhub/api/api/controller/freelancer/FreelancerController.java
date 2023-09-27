package com.devhub.api.api.controller.freelancer;

import com.devhub.api.domain.especialidade.Especialidade;
import com.devhub.api.service.especialidade.dto.EspecialidadeData;
import com.devhub.api.domain.especialidade.repository.EspecialidadeRepository;
import com.devhub.api.domain.freelancer.*;
import com.devhub.api.domain.freelancer.repository.FreelancerRepository;
import com.devhub.api.service.especialidade.dto.EspecialidadeMapper;
import com.devhub.api.service.freelancer.dto.*;
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
    private FreelancerRepository freelancerRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createFreelancer(@Valid @RequestBody CreateFreelancerData data, UriComponentsBuilder uriBuilder) {

        final Freelancer freelancer = FreelancerMapper.of(data);

        freelancerRepository.save(freelancer);

        var listaEspecialidades = data.especialidades();

        for (EspecialidadeData dataEspec : listaEspecialidades) {
            final Especialidade especialidade = EspecialidadeMapper.of(dataEspec, freelancer);
//            var especialidade = new Especialidade(dataEspec, freelancer);
            especialidadeRepository.save(especialidade);
        }

        var uri = uriBuilder.path("/freelancers/{id}").buildAndExpand(freelancer.getId_freelancer()).toUri();

        return ResponseEntity.created(uri).body(new DetailFreelancerData(freelancer));
    }

    @GetMapping
    public ResponseEntity<Page<ListFreelancerData>> listar(@PageableDefault(size = 5, sort = {"nome"})Pageable paginacao){
        var page = freelancerRepository.findAllByAtivoTrue(paginacao).map(ListFreelancerData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListFreelancerData> listarFreelancerById(@PathVariable Long id) {
        var freelancer = freelancerRepository.getReferenceById(id);
        return ResponseEntity.ok(new ListFreelancerData(freelancer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@Valid @RequestBody UpdateFreelancerData data){
        var freelancer = freelancerRepository.getReferenceById(data.id_freelancer());
        freelancer.atuallizarInformacoes(data);

        return ResponseEntity.ok(new DetailFreelancerData(freelancer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var freelancer = freelancerRepository.getReferenceById(id);
        freelancer.excluir();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity ativarConta(@PathVariable Long id){
        var freelancer = freelancerRepository.getReferenceById(id);
        freelancer.ativarConta();
        return ResponseEntity.noContent().build();
    }

}
