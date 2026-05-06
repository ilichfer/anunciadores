/*    */ package  com.anunciadores.dto;
/*    */ 
/*    */ import com.anunciadores.dto.ChatDTO;
/*    */ import com.anunciadores.dto.ContactDTO;
/*    */ import com.anunciadores.dto.FromDTO;
/*    */ 
/*    */ 
/*    */ public class MessageDTO
/*    */ {
/*    */   public int message_id;
/*    */   public FromDTO from;
/*    */   public ChatDTO chat;
/*    */   
/*    */   public MessageDTO(int message_id, FromDTO from, ChatDTO chat, int date, String text, ContactDTO contact) {
/* 15 */     this.message_id = message_id;
/* 16 */     this.from = from;
/* 17 */     this.chat = chat;
/* 18 */     this.date = date;
/* 19 */     this.text = text;
/* 20 */     this.contact = contact;
/*    */   } public int date; public String text; public ContactDTO contact;
/*    */   public MessageDTO() {}
/*    */   public int getMessage_id() {
/* 24 */     return this.message_id;
/*    */   }
/*    */   
/*    */   public void setMessage_id(int message_id) {
/* 28 */     this.message_id = message_id;
/*    */   }
/*    */   
/*    */   public FromDTO getFrom() {
/* 32 */     return this.from;
/*    */   }
/*    */   
/*    */   public void setFrom(FromDTO from) {
/* 36 */     this.from = from;
/*    */   }
/*    */   
/*    */   public ChatDTO getChat() {
/* 40 */     return this.chat;
/*    */   }
/*    */   
/*    */   public void setChat(ChatDTO chat) {
/* 44 */     this.chat = chat;
/*    */   }
/*    */   
/*    */   public int getDate() {
/* 48 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(int date) {
/* 52 */     this.date = date;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 56 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 60 */     this.text = text;
/*    */   }
/*    */   
/*    */   public ContactDTO getContact() {
/* 64 */     return this.contact;
/*    */   }
/*    */   
/*    */   public void setContact(ContactDTO contact) {
/* 68 */     this.contact = contact;
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\dto\MessageDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */