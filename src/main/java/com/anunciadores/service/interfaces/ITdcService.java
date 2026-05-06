package  com.anunciadores.service.interfaces;

import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Tdc;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ITdcService {
  Tdc save(Date paramDate, Tdc paramTdc);
  
  Tdc saveTcdImage(String paramString, Integer paramInteger);
  
  Tdc getById(int paramInt);
  
  Tdc getTdcById(int paramInt);
  
  List<TdcDto> getAll();
  
  List<TdcDto> getTdcByFecha(Date paramDate);
  
  boolean getTdcByFechaAndPersona(Date paramDate, int paramInt);
  
  List<TdcReporteDto> findAllBetweenDates(Date paramDate1, Date paramDate2) throws ParseException;
  
  TdcReporteDto findAllBetweenDatesAndPerson(Integer paramInteger) throws ParseException;
  
  List<TdcDto> findAllBetweenDatesByPersona(Date paramDate1, Date paramDate2, int paramInt);
  
  List<TdcDto> findAlltcdByPersona(int paramInt) throws ParseException;
  
  BufferedImage resizeImage(BufferedImage paramBufferedImage, int paramInt1, int paramInt2) throws IOException;
}


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\interfaces\ITdcService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */