package  com.anunciadores.client;

import com.anunciadores.client.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "prueba", url = "http://papi.colsanitas.com/osi/api/assurance/affiliations/affiliationsAndNewsManagements/contract/v1.0.0", configuration = {FeignClientConfiguration.class})
public interface PruebaFeingClient {
  @GetMapping({"/cover"})
  Object[] buscarUrl(@RequestHeader("Authorization") String paramString1, @RequestHeader("FechaPeticion") String paramString2, @RequestHeader("funcionNegocio") String paramString3, @RequestHeader("codAplicacion") String paramString4);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\client\PruebaFeingClient.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */