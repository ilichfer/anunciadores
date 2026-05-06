/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ public class PersonaReactDto
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private String name;
/*    */   private String role;
/*    */   private String ministry;
/*    */   private String email;
/*    */   private String phone;
/*    */   private String avatar;
/*    */   private Boolean active;
/*    */   
/*    */   public int getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 48 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getRole() {
/* 52 */     return this.role;
/*    */   }
/*    */   
/*    */   public void setRole(String role) {
/* 56 */     this.role = role;
/*    */   }
/*    */   
/*    */   public String getMinistry() {
/* 60 */     return this.ministry;
/*    */   }
/*    */   
/*    */   public void setMinistry(String ministry) {
/* 64 */     this.ministry = ministry;
/*    */   }
/*    */   
/*    */   public String getEmail() {
/* 68 */     return this.email;
/*    */   }
/*    */   
/*    */   public void setEmail(String email) {
/* 72 */     this.email = email;
/*    */   }
/*    */   
/*    */   public String getPhone() {
/* 76 */     return this.phone;
/*    */   }
/*    */   
/*    */   public void setPhone(String phone) {
/* 80 */     this.phone = phone;
/*    */   }
/*    */   
/*    */   public String getAvatar() {
/* 84 */     return this.avatar;
/*    */   }
/*    */   
/*    */   public void setAvatar(String avatar) {
/* 88 */     this.avatar = avatar;
/*    */   }
/*    */   
/*    */   public Boolean getActive() {
/* 92 */     return this.active;
/*    */   }
/*    */   
/*    */   public void setActive(Boolean active) {
/* 96 */     this.active = active;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\PersonaReactDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */