package br.com.fiap.hackathon.notificacao.infrastructure.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
public class EmailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${notificacao.email.remetente}")
    private String remetente;

    @Value("${notificacao.email.template.lembrete}")
    private String templateLembrete;

    public EmailSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void enviarLembreteVacina(String destinatario, String nomePaciente,
                                     String nomeVacina, String dataProximaDose) {
        try {
            Context context = new Context();
            context.setVariables(Map.of(
                    "nomePaciente", nomePaciente,
                    "nomeVacina", nomeVacina,
                    "dataProximaDose", dataProximaDose
            ));

            String corpoEmail = templateEngine.process(templateLembrete, context);
            enviarEmailHtml(destinatario, "Lembrete de Vacinação - SUS", corpoEmail);

        } catch (Exception e) {
            throw new EmailException("Falha ao gerar e-mail de lembrete", e);
        }
    }

    private void enviarEmailHtml(String destinatario, String assunto, String corpoHtml)
            throws MessagingException {

        MimeMessage mensagem = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

        helper.setFrom(remetente);
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setText(corpoHtml, true); // true indica conteúdo HTML

        mailSender.send(mensagem);
    }

    public static class EmailException extends RuntimeException {
        public EmailException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}