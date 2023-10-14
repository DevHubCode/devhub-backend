package com.devhub.api.controller;

import com.devhub.api.domain.contratante.*;
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
@RequestMapping("/contratantes")
public class ContratanteController {

    @Autowired
    private ContratanteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createFreelancer(@Valid @RequestBody CreateContratanteData data, UriComponentsBuilder uriBuilder) {

        var contratante = new Contratante(data);
//        return ResponseEntity.created().

        repository.save(contratante);

        var uri = uriBuilder.path("/contratantes/{id}").buildAndExpand(contratante.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailContratanteData(contratante));
    }

    @GetMapping
    public ResponseEntity<Page<ListContratanteData>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListContratanteData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@Valid @RequestBody UpdateContratanteData data) {
        var contratante = repository.getReferenceById(data.id());
        contratante.atuallizarInformacoes(data);

        return ResponseEntity.ok(new DetailContratanteData(contratante));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var contratante = repository.getReferenceById(id);
        contratante.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativarConta(@PathVariable Long id) {
        var contratante = repository.getReferenceById(id);
        contratante.ativarConta();
        return ResponseEntity.noContent().build();
    }
}
