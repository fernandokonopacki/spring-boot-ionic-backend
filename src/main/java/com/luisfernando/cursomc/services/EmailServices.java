package com.luisfernando.cursomc.services;

import com.luisfernando.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailServices {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

}
