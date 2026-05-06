/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ public class MinistryMember
/*    */ {
/*    */   private int positionId;
/*    */   private String position;
/*    */   private String personName;
/*    */   private String ministryName;
/*    */   private int personId;
/*    */   
/*    */   public MinistryMember() {}
/*    */   
/*    */   public MinistryMember(String position, String personName, String ministryName, int positionId, int personId) {
/* 14 */     this.position = position;
/* 15 */     this.personName = personName;
/* 16 */     this.ministryName = ministryName;
/* 17 */     this.positionId = positionId;
/* 18 */     this.personId = personId;
/*    */   }
/*    */   
/*    */   public String getPosition() {
/* 22 */     return this.position;
/*    */   }
/*    */   
/*    */   public String getPersonName() {
/* 26 */     return this.personName;
/*    */   }
/*    */   
/*    */   public void setPosition(String position) {
/* 30 */     this.position = position;
/*    */   }
/*    */   
/*    */   public void setPersonName(String personName) {
/* 34 */     this.personName = personName;
/*    */   }
/*    */   
/*    */   public String getMinistryName() {
/* 38 */     return this.ministryName;
/*    */   }
/*    */   
/*    */   public int getPositionId() {
/* 42 */     return this.positionId;
/*    */   }
/*    */   
/*    */   public void setPositionId(int positionId) {
/* 46 */     this.positionId = positionId;
/*    */   }
/*    */   
/*    */   public void setMinistryName(String ministryName) {
/* 50 */     this.ministryName = ministryName;
/*    */   }
/*    */   
/*    */   public int getPersonId() {
/* 54 */     return this.personId;
/*    */   }
/*    */   
/*    */   public void setPersonId(int personId) {
/* 58 */     this.personId = personId;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MinistryMember.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */