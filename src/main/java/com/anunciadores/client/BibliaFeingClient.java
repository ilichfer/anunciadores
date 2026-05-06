package  com.anunciadores.client;

import com.anunciadores.client.FeignClientConfiguration;
import com.anunciadores.dto.CapitulosDto;
import com.anunciadores.dto.LibrosDto;
import com.anunciadores.dto.VersiculoResponseDto;
import com.anunciadores.dto.VersiculosDto;
import com.anunciadores.dto.VersionBiblesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "biblia", url = "https://api.scripture.api.bible/v1/bibles", configuration = {FeignClientConfiguration.class})
public interface BibliaFeingClient {
  @GetMapping({"?language=spa"})
  VersionBiblesDto buscarBiblia(@RequestHeader(value = "api-key", required = true) String paramString);
  
  @GetMapping({"/{idBible}/books"})
  LibrosDto buscarLibro(@PathVariable("idBible") String paramString1, @RequestHeader(value = "api-key", required = true) String paramString2);
  
  @GetMapping({"/{idBible}/books/{idBook}/chapters"})
  CapitulosDto buscarCapitulos(@PathVariable("idBible") String paramString1, @PathVariable("idBook") String paramString2, @RequestHeader(value = "api-key", required = true) String paramString3);
  
  @GetMapping({"/{idBible}/chapters/{idChapter}/verses"})
  VersiculosDto buscarVersiculos(@PathVariable("idBible") String paramString1, @PathVariable("idChapter") String paramString2, @RequestHeader(value = "api-key", required = true) String paramString3);
  
  @GetMapping({"/{idBible}/verses/{idVerse}?content-type=text"})
  VersiculoResponseDto buscarVersiculo(@PathVariable("idBible") String paramString1, @PathVariable("idVerse") String paramString2, @RequestHeader(value = "api-key", required = true) String paramString3);
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\client\BibliaFeingClient.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */