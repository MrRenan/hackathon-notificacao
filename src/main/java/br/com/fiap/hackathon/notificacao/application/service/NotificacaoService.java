package br.com.fiap.hackathon.notificacao.application.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificacaoService {

    public void enviarNotificacao(String cpf, String nomeVacina, LocalDate dataProximaDose) {
        // Mock inicial - implementaremos o envio real depois
        System.out.printf(
                "NOTIFICAÇÃO: CPF %s - Próxima dose da vacina %s em %s%n",
                cpf, nomeVacina, dataProximaDose
        );
    }
}