package com.anunciadores.dto;

/**
 *
 * @author valbuena
 */


public class VersiculoDto {

	private String id;					
	private String orgId;				
	private String bibleId	;
	private String bookId;	
	private String chapterId;
	private String content;			
	private String reference;			
	private String verseCount;			
	private String copyright;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getBibleId() {
		return bibleId;
	}
	public void setBibleId(String bibleId) {
		this.bibleId = bibleId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
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
	public String getVerseCount() {
		return verseCount;
	}
	public void setVerseCount(String verseCount) {
		this.verseCount = verseCount;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
}
