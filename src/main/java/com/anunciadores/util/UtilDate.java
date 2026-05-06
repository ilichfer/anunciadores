/*    */ package  com.anunciadores.util;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.time.LocalDate;
/*    */ import java.time.ZoneId;
/*    */ import java.time.ZonedDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import java.util.Date;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UtilDate
/*    */ {
/*    */   public String cargarfechaActualBogotaString() throws ParseException {
/* 17 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/* 18 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 19 */     String fechaActualStr = nowInBogota.format(formatter);
/* 20 */     return fechaActualStr;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date cargarfechaActualBogotaDate() throws ParseException {
/* 25 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 26 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/* 27 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 28 */     String fechaActualStr = nowInBogota.format(formatter);
/* 29 */     Date actualDate = sdf.parse(fechaActualStr);
/* 30 */     return actualDate;
/*    */   }
/*    */   
/*    */   public String convertDateToString(Date fecha) {
/* 34 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/* 35 */     String fechaString = sdf.format(fecha);
/* 36 */     return fechaString;
/*    */   }
/*    */ 
/*    */   
/*    */   public Date convertStringToDate(String fecha) throws ParseException {
/* 41 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 42 */     Date fechaDate = sdf.parse(fecha);
/* 43 */     return fechaDate;
/*    */   }
/*    */ 
/*    */   
/*    */   public String convertDateToStringWithFormat(Date fecha, String format) throws ParseException {
/* 48 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 49 */     String fechaString = sdf.format(fecha);
/* 50 */     return fechaString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String cargarFechaBogotaConParametro(String format) throws ParseException {
/* 55 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
/* 56 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 57 */     String fechaActualStr = nowInBogota.format(formatter);
/* 58 */     return fechaActualStr;
/*    */   }
/*    */   
/*    */   public Date convertLocaldateToDate(LocalDate local) {
/* 62 */     LocalDate localDate = local;
/*    */ 
/*    */     
/* 65 */     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
/*    */     
/* 67 */     System.out.println("LocalDate: " + localDate);
/* 68 */     System.out.println("Date: " + date);
/* 69 */     return date;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadore\\util\UtilDate.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */