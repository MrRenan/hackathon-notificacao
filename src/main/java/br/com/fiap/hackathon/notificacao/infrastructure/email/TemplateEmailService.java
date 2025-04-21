package br.com.fiap.hackathon.notificacao.infrastructure.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateEmailService {

    private final TemplateEngine templateEngine;

    public String gerarTemplateLembrete(String nomePaciente, String vacina, String dataProximaDose) {
        Context context = new Context();
        context.setVariables(Map.of(
                "nomePaciente", nomePaciente,
                "vacina", vacina,
                "dataProximaDose", dataProximaDose
        ));
        return templateEngine.process("email/lembrete-vacina", context);
    }
}