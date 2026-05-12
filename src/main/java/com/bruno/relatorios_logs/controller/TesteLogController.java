package com.bruno.relatorios_logs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteLogController {

    private static final Logger logger =
            LoggerFactory.getLogger(TesteLogController.class);

    @GetMapping("/teste-log/teste")
    public String testarLog() {

        MDC.put("service", "relatorios-logs");
        MDC.put("endpoint", "/teste-log/teste");
        MDC.put("method", "GET");
        MDC.put("user_id", "15");

        logger.info("Teste de log INFO enviado para o Graylog");
        logger.warn("Teste de log WARN enviado para o Graylog");
        logger.error("Teste de log ERROR enviado para o Graylog");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        MDC.clear();

        return "Logs enviados para o Graylog";
    }
}