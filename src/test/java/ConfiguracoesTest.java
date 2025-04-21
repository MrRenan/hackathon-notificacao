package br.com.fiap.hackathon.notificacao.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConfiguracoesTest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Queue queue;

    @Test
    void deveCarregarConfiguracoesCorretamente() {
        assertNotNull(mailSender);
        assertNotNull(queue);
        assertEquals("vacina.aplicada", queue.getName());
    }
}