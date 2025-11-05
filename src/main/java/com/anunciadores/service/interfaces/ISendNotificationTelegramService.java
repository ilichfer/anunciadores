package com.anunciadores.service.interfaces;

import com.anunciadores.model.Sugerencia;


public interface ISendNotificationTelegramService {
	public String sendNotificationTelegram(Sugerencia saveSugerencia,Integer idPersona);
}
