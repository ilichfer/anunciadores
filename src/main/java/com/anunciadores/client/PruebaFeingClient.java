package com.anunciadores.client;

import com.anunciadores.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "prueba" ,url = "http://papi.colsanitas.com/osi/api/assurance/affiliations/affiliationsAndNewsManagements/contract/v1.0.0", configuration = FeignClientConfiguration.class)
public interface PruebaFeingClient {
	
	
	@GetMapping("/cover")
	public Object[] buscarUrl(@RequestHeader("Authorization") String header,
							  @RequestHeader("FechaPeticion") String FechaPeticion,
							  @RequestHeader("funcionNegocio") String funcionNegocio,
							  @RequestHeader("codAplicacion") String codAplicacion);


}
