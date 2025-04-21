# 🔔 Serviço de Notificação - Carteira Vacinal SUS

Microsserviço responsável por enviar notificações automáticas sobre vacinas (próximas doses, campanhas) para pacientes e profissionais de saúde no SUS.

## 📋 Fluxo Principal
```mermaid
graph LR
    A[Carteira Service] -->|Publica Evento| B[(RabbitMQ)]
    B -->|Consome Mensagem| C[Notificação Service]
    C --> D[Envia E-mail/SMS]
    C --> E[Registra Log]

