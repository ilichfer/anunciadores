/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ public class ChatDTO
/*    */ {
/*    */   public Long id;
/*    */   public String first_name;
/*    */   public String last_name;
/*    */   public String type;
/*    */   
/*    */   public ChatDTO() {}
/*    */   
/*    */   public ChatDTO(Long id, String first_name, String last_name, String type) {
/* 13 */     this.id = id;
/* 14 */     this.first_name = first_name;
/* 15 */     this.last_name = last_name;
/* 16 */     this.type = type;
/*    */   }
/*    */   
/*    */   public Long getId() {
/* 20 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 24 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getFirst_name() {
/* 28 */     return this.first_name;
/*    */   }
/*    */   
/*    */   public void setFirst_name(String first_name) {
/* 32 */     this.first_name = first_name;
/*    */   }
/*    */   
/*    */   public String getLast_name() {
/* 36 */     return this.last_name;
/*    */   }
/*    */   
/*    */   public void setLast_name(String last_name) {
/* 40 */     this.last_name = last_name;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 44 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 48 */     this.type = type;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ChatDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */