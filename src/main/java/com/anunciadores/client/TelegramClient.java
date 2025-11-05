package com.anunciadores.client;

import com.anunciadores.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "telegramClient", url = "https://api.telegram.org", configuration = FeignClientConfiguration.class)
public interface TelegramClient {

	@PostMapping(value = "/bot{token}/sendMessage", consumes = "application/json")
	String enviarMensajeTelegram(
			@PathVariable("token") String token,
			@RequestBody Map<String, Object> body,
			@RequestParam("parse_mode") String parseMode

	);

	@GetMapping(value = "/bot{token}/getUpdates")
	public ResponseTelegram getUpdates(@PathVariable("token") String token);


}




