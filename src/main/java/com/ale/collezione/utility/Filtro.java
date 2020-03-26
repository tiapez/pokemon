package com.ale.collezione.utility;

public class Filtro {
	
	String rarita="%";
	String lingua="Ita";
	String condizione="%";
	String set="%";
	
	public String getRarita() {
		return rarita;
	}
	public void setRarita(String rarita) {
		this.rarita = rarita;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getCondizione() {
		return condizione;
	}
	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	@Override
	public String toString() {
		return "Filtro [rarita=" + rarita + ", lingua=" + lingua + ", condizione=" + condizione + ", set=" + set + "]";
	}
	
	
	

}
