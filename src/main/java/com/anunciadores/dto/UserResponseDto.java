/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.model.Persona;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class UserResponseDto
/*    */ {
/*    */   private Long id;
/*    */   private String name;
/*    */   private String role;
/*    */   private String email;
/*    */   private String phone;
/*    */   private String avatar;
/*    */   private List<String> ministry;
/*    */   
/*    */   public UserResponseDto(Persona p) {
/* 18 */     this.id = Long.valueOf(p.getId().longValue());
/* 19 */     this.name = (p.getNombre() + " " + p.getNombre()).trim();
/* 20 */     this.role = "ADMINISTRADOR";
/* 21 */     this.email = p.getEmail();
/* 22 */     this.phone = (p.getCelular() != null) ? p.getCelular() : p.getTelefono();
/* 23 */     this.avatar = null;
/* 24 */     this.ministry = List.of();
/*    */   }
/*    */   
/* 27 */   public Long getId() { return this.id; }
/* 28 */   public String getName() { return this.name; }
/* 29 */   public String getRole() { return this.role; }
/* 30 */   public String getEmail() { return this.email; }
/* 31 */   public String getPhone() { return this.phone; }
/* 32 */   public String getAvatar() { return this.avatar; } public List<String> getMinistry() {
/* 33 */     return this.ministry;
/*    */   }
/*    */   public void setId(Long id) {
/* 36 */     this.id = id;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 40 */     this.name = name;
/*    */   }
/*    */   
/*    */   public void setRole(String role) {
/* 44 */     this.role = role;
/*    */   }
/*    */   
/*    */   public void setEmail(String email) {
/* 48 */     this.email = email;
/*    */   }
/*    */   
/*    */   public void setPhone(String phone) {
/* 52 */     this.phone = phone;
/*    */   }
/*    */   
/*    */   public void setAvatar(String avatar) {
/* 56 */     this.avatar = avatar;
/*    */   }
/*    */   
/*    */   public void setMinistry(List<String> ministry) {
/* 60 */     this.ministry = ministry;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\UserResponseDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */