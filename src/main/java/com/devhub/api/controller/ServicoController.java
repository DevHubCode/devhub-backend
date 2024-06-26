package com.devhub.api.controller;

import com.devhub.api.domain.contratante.Contratante;
import com.devhub.api.domain.contratante.ContratanteRepository;
import com.devhub.api.domain.freelancer.Freelancer;
import com.devhub.api.domain.freelancer.FreelancerRepository;
import com.devhub.api.domain.funcao.Funcao;
//import com.devhub.api.domain.servico.CreateServicoDTO;
import com.devhub.api.domain.servico.DetailServicoDTO;
import com.devhub.api.domain.servico.FinishServicoDTO;
import com.devhub.api.domain.servico.Servico;
import com.devhub.api.domain.servico.ServicoRepository;
import com.devhub.api.service.EmailService;
import com.devhub.api.service.ServicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private EmailService emailService;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity criarServico(@RequestParam Long idContratante,
                                          @RequestParam Long idFreelancer,
                                          @RequestParam String destinatario,
                                          @RequestParam String nomeDestinatario,
                                          @RequestParam String nomeRemetente,
                                          UriComponentsBuilder uriBuilder) {
        var servico = service.criarServico(idFreelancer, idContratante);
        var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servico.getId()).toUri();
        String mensagem = "Olá " + nomeDestinatario + ",\n\n" +
                "Esperamos que esteja tudo bem com você.\n\n" +
                "Ficamos felizes em informar que o(a) contratante " + nomeRemetente + " tem interesse nos seus serviços e gostaria de negociar o seu tempo para trabalhar.\n\n" +
                "Se você estiver disponível para discutir os detalhes desta proposta, em breve o contratante irá entrar em contato.\n\n" +
                "Estamos aqui para ajudar e esperamos que essa oportunidade seja benéfica para ambas as partes.\n\n" +
                "Atenciosamente,\n" +
                "Equipe DevHub";
        emailService.enviarEmailTexto(destinatario, "Nova proposta de Freelancer", mensagem);
        return ResponseEntity.created(uri).body(new DetailServicoDTO(servico));
    }

    @PatchMapping("/concluir")
    @Transactional
    public ResponseEntity concluirServico(@RequestBody FinishServicoDTO data) {
        service.concluirServico(data);
        String mensagem = "Olá " + data.nomeDestinatario() + ",\n\n" +
                "Esperamos que este e-mail o encontre bem!\n\n" +
                "Gostaríamos de informar que o(a) " + data.nomeRemetente() + " finalizou o seu contrato com sucesso.\n" +
                "Ele(a) lhe pagou um total de R$" + data.valorHora() + " pelas horas trabalhadas.\n\n" +
                "Queremos agradecer pelo seu excelente trabalho e dedicação ao longo deste período!\n\n" +
                "Se tiver alguma dúvida ou precisar de alguma informação adicional, por favor, não hesite em entrar em contato.\n\n" +
                "Atenciosamente,\n" +
                "Equipe DevHub";
        emailService.enviarEmailTexto(data.destinatario(), "Contrato finalizado", mensagem);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/fechar")
    @Transactional
    public ResponseEntity fecharServico(@RequestParam("idContratante")  Long idContratante,
                                        @RequestParam("idFreelancer") Long idFreelancer,
                                        @RequestParam String destinatario,
                                        @RequestParam String nomeDestinatario,
                                        @RequestParam String nomeRemetente) {
        service.fecharServico(idContratante, idFreelancer);
        String mensagem = "Olá " + nomeDestinatario + ",\n\n" +
                "Espero que esteja tudo bem com você.\n\n" +
                "Gostaríamos de informar que o(a) contratante " + nomeRemetente + " entrou em contato, mas infelizmente não houve um avanço na comunicação.\n\n" +
                "Se precisar de mais alguma informação ou se tiver alguma dúvida, por favor, não hesite em nos contatar.\n\n" +
                "Agradecemos pela sua compreensão e esperamos poder resolver qualquer problema ou dúvida que possa surgir.\n\n" +
                "Atenciosamente,\n" +
                "Equipe DevHub";

        emailService.enviarEmailTexto(destinatario, "Contato cancelado", mensagem);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public boolean validarAndamento(@RequestParam("idContratante") Long idContratante,
                                    @RequestParam("idFreelancer") Long idFreelancer) {
        return service.verificarServicoEmAndamento(idContratante, idFreelancer);
    }

//
//    @PostMapping("/txt")
//    public ResponseEntity criarServicoPorTxt(@RequestParam("file")MultipartFile arquivoTXT) throws IOException {
//        InputStream inputStream = arquivoTXT.getInputStream();
//        Stream<String> txt = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//                .lines();
//
//        int cont = 0;
//        int contServico = 0;
//        int horasTrabalhadas = 0;
//        Freelancer freelancer = null;
//        Contratante contratante = null;
//        for (String linha  : txt.collect(Collectors.toList())) {
//            if (linha.substring(0,2).equals("00")) {
//                System.out.println("É um linha de header");
//            } else if (linha.substring(0,2).equals("01")) {
//                System.out.println("É um linha de trailer");
//            } else if (linha.substring(0,2).equals("02")) {
//                System.out.println("É um linha de corpo");
//
//                horasTrabalhadas = Integer.parseInt(linha.substring(2, 5).trim());
//                String cnpj = linha.substring(5,19);
//                contratante = contratanteRepository.findByCnpj(cnpj);
//                System.out.println(contratante);
//            } else if (linha.substring(0,2).equals("03")) {
//
//                String nomeFreelancer = linha.substring(2,43).trim();
//                System.out.println("nome: "+nomeFreelancer);
//                String telefone = linha.substring(43, 54);
//                System.out.println("telefone: "+telefone);
//                String email = linha.substring(54, 104).trim();
//                System.out.println("email: "+email);
//                String funcao = Funcao.valueOf(linha.substring(105, 137).trim()).getFuncao();
//                Double valorHora = Double.valueOf(linha.substring(137, 144)
//                        .trim()
//                        .replace(",", "."));
//                System.out.println("valor: "+ valorHora);
//                String senioridade = linha.substring(144, 152).trim();
//                System.out.println("senioridade: "+senioridade);
//                freelancer =
//                        freelancerRepository.findByNomeAndTelefoneAndEmailAndValorHoraAndSenioridade(nomeFreelancer, telefone, email, valorHora, senioridade);
//                System.out.println(freelancer);
//                cont+=2;
//            } else {
//                System.out.println("Registro inválido");
//            }
//
//            if (cont % 2 == 0 && cont > 0) {
//                listaLida.add(
//                        new Servico(
//                                new CreateServicoDTO(horasTrabalhadas), contratante, freelancer
//                        )
//                );
//            }
//        }
//        servicoRepository.saveAll(listaLida);
//        return ResponseEntity.ok().build();
//    }
}
