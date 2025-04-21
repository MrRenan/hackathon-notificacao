package br.com.fiap.hackathon.notificacao.infrastructure.rabbitmq;

import br.com.fiap.hackathon.notificacao.domain.model.VacinaAplicadaEvent;
import br.com.fiap.hackathon.notificacao.application.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    private final NotificacaoService notificacaoService;

    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = "vacina.aplicada")
    public void consumir(VacinaAplicadaEvent event) {
        notificacaoService.enviarNotificacao(
                event.cpf(),
                event.nomeVacina(),
                event.dataProximaDose()
        );
    }
}