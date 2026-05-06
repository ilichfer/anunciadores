/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.MesaDto;
/*    */ import java.util.List;
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
/*    */ public class ActividadDto
/*    */ {
/*    */   private int id;
/*    */   private String nombreActividad;
/*    */   private String fecha;
/*    */   private int cantidadMesas;
/*    */   private List<MesaDto> mesas;
/*    */   
/*    */   public int getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 34 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getNombreActividad() {
/* 38 */     return this.nombreActividad;
/*    */   }
/*    */   
/*    */   public void setNombreActividad(String nombreActividad) {
/* 42 */     this.nombreActividad = nombreActividad;
/*    */   }
/*    */   
/*    */   public String getFecha() {
/* 46 */     return this.fecha;
/*    */   }
/*    */   
/*    */   public void setFecha(String fecha) {
/* 50 */     this.fecha = fecha;
/*    */   }
/*    */   
/*    */   public List<MesaDto> getMesas() {
/* 54 */     return this.mesas;
/*    */   }
/*    */   
/*    */   public void setMesas(List<MesaDto> mesas) {
/* 58 */     this.mesas = mesas;
/*    */   }
/*    */   
/*    */   public int getCantidadMesas() {
/* 62 */     return this.cantidadMesas;
/*    */   }
/*    */   
/*    */   public void setCantidadMesas(int cantidadMesas) {
/* 66 */     this.cantidadMesas = cantidadMesas;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\ActividadDto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */