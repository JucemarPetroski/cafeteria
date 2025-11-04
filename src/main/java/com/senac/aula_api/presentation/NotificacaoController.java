package com.senac.aula_api.presentation;

import com.senac.aula_api.domain.entities.NotificacaoEmail;
import com.senac.aula_api.domain.entities.NotificacaoSms;
import com.senac.aula_api.application.dto.NotificacaoEmailRequestDto;
import com.senac.aula_api.application.dto.NotificacaoSmsRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {
    @PostMapping("/email")
    public void enviarEmail(@RequestBody NotificacaoEmailRequestDto dto) {
        NotificacaoEmail email = new NotificacaoEmail(dto.getEmailDestino());
        email.Enviar(dto.getMensagem());
    }

    @PostMapping("/sms")
    public void enviarSms(@RequestBody NotificacaoSmsRequestDto dto) {
        NotificacaoSms sms = new NotificacaoSms(dto.getTelefoneDestino());
        sms.Enviar(dto.getMensagem());
    }
}
