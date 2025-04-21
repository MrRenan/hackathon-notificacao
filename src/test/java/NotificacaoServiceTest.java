package br.com.fiap.hackathon.notificacao.application.service;

import br.com.fiap.hackathon.notificacao.infrastructure.email.EmailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceTest {

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @Test
    void deveEnviarNotificacaoPorEmail() {
        String cpf = "123.456.789-00";
        String nomeVacina = "COVID-19";
        LocalDate dataProximaDose = LocalDate.now().plusMonths(1);

        notificacaoService.enviarNotificacao(cpf, nomeVacina, dataProximaDose);

        verify(emailSender, times(1))
                .enviarLembreteVacina(cpf, "Paciente", nomeVacina, dataProximaDose.toString());
    }
}