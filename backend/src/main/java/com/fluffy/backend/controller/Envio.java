package com.fluffy.backend.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fluffy.backend.entity.NotificationStock;
import com.fluffy.backend.service.NotificationStockService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class Envio {

	@Autowired
	private NotificationStockService notificationStockService;

//    @Value("${twilio.accountSid}")
	private String accountSid;
//
//    @Value("${twilio.authToken}")
	private String authToken;
//
//    @Value("${twilio.destino}")
	private String destino;
//
//    @Value("${twilio.origem}")
	private String origem;

//    @PostConstruct
	public ResponseEntity<List<NotificationStock>> buscarTodasNotificationStocks() {
		List<NotificationStock> notificationStocks = notificationStockService.buscarTodasNotificationStocks();

		for (NotificationStock notificationStock : notificationStocks) {
			String status = notificationStock.getNsStatus();
			System.out.println(status);
			if (status != null && status.equalsIgnoreCase("aberto")) {
				Twilio.init(accountSid, authToken);
				System.out.println(status);

				String mensagem = "Oracle Dinner - Estoque Baixo: " + "O insumo "
						+ notificationStock.getStocks().getName() + " está com estoque baixo. Quantidade atual: "
						+ notificationStock.getStocks().getAmountAvailable() + " "
						+ notificationStock.getStocks().getMeasurement()
						+ ". Acesse a plataforma para mais informações: https://www.oracledinner.vercel.app";

				Message message = Message.creator(new PhoneNumber(destino), new PhoneNumber(origem), mensagem).create();

				System.out.println(message.getSid());
			} else {
				System.out.println("Campos não preenchidos. A mensagem não foi enviada.");
			}
		}

		return ResponseEntity.ok(notificationStocks);
	}
}
