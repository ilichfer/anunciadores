package com.anunciadores.dto;

public class LanguageDto {

	private String id;
	private String name;
	private String nameLocal;
	private String script;
	private String scriptDirection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getScriptDirection() {
		return scriptDirection;
	}

	public void setScriptDirection(String scriptDirection) {
		this.scriptDirection = scriptDirection;
	}

}
