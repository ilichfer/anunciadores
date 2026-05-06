/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.model.TimeSlot;
/*    */ import com.anunciadores.service.interfaces.IReserveHourService;
/*    */ import java.time.LocalDate;
/*    */ import java.time.LocalTime;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class ReserveHourServiceImpl
/*    */   implements IReserveHourService
/*    */ {
/* 32 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.service.ReserveHourServiceImpl.class);
/*    */ 
/*    */   
/*    */   public List<TimeSlot> reserveSlot() {
/* 36 */     List<TimeSlot> slots = new ArrayList<>();
/* 37 */     LocalDate today = LocalDate.now();
/* 38 */     LocalTime start = LocalTime.of(7, 0);
/* 39 */     int totalBlocks = 48;
/*    */     
/* 41 */     for (int i = 0; i < totalBlocks; i++) {
/* 42 */       LocalTime blockStart = start.plusMinutes((i * 30));
/* 43 */       LocalDate blockDate = today;
/*    */       
/* 45 */       TimeSlot slot = new TimeSlot();
/* 46 */       slot.setDiaSiguiente(false);
/*    */       
/* 48 */       if (blockStart.isBefore(start)) {
/* 49 */         blockDate = today.plusDays(1L);
/* 50 */         slot.setDiaSiguiente(true);
/*    */       } 
/*    */       
/* 53 */       slot.setDate(blockDate);
/* 54 */       slot.setStartTime(blockStart);
/* 55 */       slot.setReserved(false);
/* 56 */       slots.add(slot);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 74 */     return slots;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\ReserveHourServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */