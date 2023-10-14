package com.devhub.api.domain.contratante;

import com.devhub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contratantes")
@Entity(name = "Contratante")
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contratante extends Usuario {

    private String cnpj;

    public Contratante(CreateContratanteData data) {
        super(data.nome(), data.telefone(), data.email(), data.senha());
        this.cnpj = data.cnpj();
    }

    public void atuallizarInformacoes(UpdateContratanteData data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if(data.senha() != null){
            this.senha = data.senha();
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    public void ativarConta() {
        this.ativo = true;
    }

}
