package br.algaworks.Iago.Food.domain.service;

import lombok.*;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Builder
    @Getter
    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;

        @NonNull
        private String templateHtmlCorpo;

        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}
