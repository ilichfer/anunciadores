package com.anunciadores.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class BibleFeingDto {

	    private String id;
	    private String dblID;
	    private Object relatedDbl;
	    private String name;
	    private String nameLocal;
	    private String abbreviation;
	    private String abbreviationLocal;
	    private String description;
	    private String descriptionLocal;
	    private LanguageDto language;
	    private List<CountryDto> countries;
	    private String type;
	    private OffsetDateTime updatedAt;
	    private List<Object> audioBibles;
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDblID() {
			return dblID;
		}
		public void setDblID(String dblID) {
			this.dblID = dblID;
		}
		public Object getRelatedDbl() {
			return relatedDbl;
		}
		public void setRelatedDbl(Object relatedDbl) {
			this.relatedDbl = relatedDbl;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNameLocal() {
			return nameLocal;
		}
		public void setNameLocal(String nameLocal) {
			this.nameLocal = nameLocal;
		}
		public String getAbbreviation() {
			return abbreviation;
		}
		public void setAbbreviation(String abbreviation) {
			this.abbreviation = abbreviation;
		}
		public String getAbbreviationLocal() {
			return abbreviationLocal;
		}
		public void setAbbreviationLocal(String abbreviationLocal) {
			this.abbreviationLocal = abbreviationLocal;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDescriptionLocal() {
			return descriptionLocal;
		}
		public void setDescriptionLocal(String descriptionLocal) {
			this.descriptionLocal = descriptionLocal;
		}
		public LanguageDto getLanguage() {
			return language;
		}
		public void setLanguage(LanguageDto language) {
			this.language = language;
		}
		public List<CountryDto> getCountries() {
			return countries;
		}
		public void setCountries(List<CountryDto> countries) {
			this.countries = countries;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public OffsetDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(OffsetDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		public List<Object> getAudioBibles() {
			return audioBibles;
		}
		public void setAudioBibles(List<Object> audioBibles) {
			this.audioBibles = audioBibles;
		}
	    
	    

	}


	
	
	

