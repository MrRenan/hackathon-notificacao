package br.com.fiap.hackathon.notificacao.domain.model;

import java.time.LocalDate;

public record VacinaAplicadaEvent(
        String cpf,
        String nomeVacina,
        LocalDate dataProximaDose
) {}