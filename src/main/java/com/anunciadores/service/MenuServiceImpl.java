/*    */ package  com.anunciadores.service;
/*    */ 
/*    */ import com.anunciadores.model.ParamMenu;
/*    */ import com.anunciadores.model.PermisosMenu;
/*    */ import com.anunciadores.repository.IParamMenuRepo;
/*    */ import com.anunciadores.repository.IPermisosRepo;
/*    */ import com.anunciadores.service.interfaces.IMenuService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
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
/*    */ @Service
/*    */ public class MenuServiceImpl
/*    */   implements IMenuService
/*    */ {
/*    */   @Autowired
/*    */   private IPermisosRepo permisosMenuRepo;
/*    */   @Autowired
/*    */   private IParamMenuRepo paramMenuRepo;
/*    */   
/*    */   public List<PermisosMenu> findAllPermisosMenu(int idPersona) {
/* 32 */     List<PermisosMenu> listaResposePermisos = new ArrayList<>();
/*    */     
/* 34 */     List<PermisosMenu> listaPersmisos = this.permisosMenuRepo.findByIdPersona(idPersona);
/*    */     
/* 36 */     List<ParamMenu> listaMenu = this.paramMenuRepo.findAll();
/* 37 */     listaMenu.forEach(p -> listaResposePermisos.add(comparaListas(listaPersmisos, p)));
/*    */     
/* 39 */     return listaResposePermisos;
/*    */   }
/*    */   
/*    */   private PermisosMenu comparaListas(List<PermisosMenu> permiso, ParamMenu opcionMenu) {
/* 43 */     PermisosMenu permisoNoencontrado = new PermisosMenu();
/* 44 */     for (PermisosMenu perm : permiso) {
/* 45 */       if (perm.getNombreBotonMenu().equals(opcionMenu.getNombreBotonMenu())) {
/* 46 */         return perm;
/*    */       }
/* 48 */       permisoNoencontrado.setNombreBotonMenu(opcionMenu.getNombreBotonMenu());
/* 49 */       permisoNoencontrado.setIdPersona(perm.getIdPersona());
/* 50 */       permisoNoencontrado.setEstado("false");
/* 51 */       permisoNoencontrado.setMenu(opcionMenu);
/*    */     } 
/* 53 */     return permisoNoencontrado;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\MenuServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */