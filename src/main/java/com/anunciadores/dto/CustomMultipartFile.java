/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ public class CustomMultipartFile
/*    */   implements MultipartFile {
/*    */   public CustomMultipartFile(byte[] imgContent) {
/* 13 */     this.imgContent = imgContent;
/*    */   }
/*    */   
/*    */   private final byte[] imgContent;
/*    */   
/*    */   public String getName() {
/* 19 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getOriginalFilename() {
/* 25 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getContentType() {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 36 */     return (this.imgContent == null || this.imgContent.length == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSize() {
/* 41 */     return this.imgContent.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBytes() throws IOException {
/* 46 */     return this.imgContent;
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getInputStream() throws IOException {
/* 51 */     return new ByteArrayInputStream(this.imgContent);
/*    */   }
/*    */ 
/*    */   
/*    */   public void transferTo(File dest) throws IOException, IllegalStateException {
/* 56 */     (new FileOutputStream(dest)).write(this.imgContent);
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\CustomMultipartFile.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */