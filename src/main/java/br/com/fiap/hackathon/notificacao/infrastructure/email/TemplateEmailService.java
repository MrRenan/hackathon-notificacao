package br.com.fiap.hackathon.notificacao.infrastructure.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.naming.Context;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TemplateEmailService {

    private final TemplateEngine templateEngine;

    public String gerarTemplateLembrete(String nomePaciente, String vacina, LocalDate data) {
        Context ctx = new Context();
        ctx.setVariable("nome", nomePaciente);
        ctx.setVariable("vacina", vacina);
        ctx.setVariable("data", data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return templateEngine.process("emails/lembrete-vacina", ctx);
    }
}