package com.anunciadores.dto;

/**
 *
 * @author valbuena
 */


public class CapituloDto {

	private String id;					
	private String bibleId;				
	private String number	;
	private String bookId;	
	private String content;
	private String reference;	
	
//	private Integer verseCount;			
//	private String next;		
//	private String previous;	
//	private String copyright;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBibleId() {
		return bibleId;
	}
	public void setBibleId(String bibleId) {
		this.bibleId = bibleId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
            
}
