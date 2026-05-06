/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.CordinatorDto;
/*    */ import com.anunciadores.dto.MinistryDto;
/*    */ import java.time.LocalDate;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProgramationDto
/*    */ {
/*    */   private String id;
/*    */   private LocalDate date;
/*    */   private String time;
/*    */   private CordinatorDto coordinator;
/*    */   private List<MinistryDto> ministries;
/*    */   
/*    */   public ProgramationDto() {}
/*    */   
/*    */   public ProgramationDto(String id, LocalDate date, String time, CordinatorDto coordinator, List<MinistryDto> ministries) {
/* 22 */     this.id = id;
/* 23 */     this.date = date;
/* 24 */     this.time = time;
/* 25 */     this.coordinator = coordinator;
/* 26 */     this.ministries = ministries;
/*    */   }
/*    */   
/*    */   public CordinatorDto getCoordinator() {
/* 30 */     return this.coordinator;
/*    */   }
/*    */   
/*    */   public void setCoordinator(CordinatorDto coordinator) {
/* 34 */     this.coordinator = coordinator;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   
/*    */   public LocalDate getDate() {
/* 46 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(LocalDate date) {
/* 50 */     this.date = date;
/*    */   }
/*    */   
/*    */   public String getTime() {
/* 54 */     return this.time;
/*    */   }
/*    */   
/*    */   public void setTime(String time) {
/* 58 */     this.time = time;
/*    */   }
/*    */   
/*    */   public List<MinistryDto> getMinistries() {
/* 62 */     return this.ministries;
/*    */   }
/*    */   
/*    */   public void setMinistries(List<MinistryDto> ministries) {
/* 66 */     this.ministries = ministries;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ProgramationDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */