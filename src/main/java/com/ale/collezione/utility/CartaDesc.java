package com.ale.collezione.utility;

public class CartaDesc {

	private String id;
	private String nome;
	private String rarita;
	private String condizione1;
	private String condizione2;
	private String condizione3;
	private String condizione4;
	private String condizione5;
	private int qta1;
	private int qta2;
	private int qta3;
	private int qta4;
	private int qta5;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRarita() {
		return rarita;
	}

	public void setRarita(String rarita) {
		this.rarita = rarita;
	}

	public String getCondizione1() {
		return condizione1;
	}

	public void setCondizione1(String condizione1) {
		this.condizione1 = condizione1;
	}

	public String getCondizione2() {
		return condizione2;
	}

	public void setCondizione2(String condizione2) {
		this.condizione2 = condizione2;
	}

	public String getCondizione3() {
		return condizione3;
	}

	public void setCondizione3(String condizione3) {
		this.condizione3 = condizione3;
	}

	public String getCondizione4() {
		return condizione4;
	}

	public void setCondizione4(String condizione4) {
		this.condizione4 = condizione4;
	}

	public String getCondizione5() {
		return condizione5;
	}

	public void setCondizione5(String condizione5) {
		this.condizione5 = condizione5;
	}

	public int getQta1() {
		return qta1;
	}

	public void setQta1(int qta1) {
		this.qta1 = qta1;
	}

	public int getQta2() {
		return qta2;
	}

	public void setQta2(int qta2) {
		this.qta2 = qta2;
	}

	public int getQta3() {
		return qta3;
	}

	public void setQta3(int qta3) {
		this.qta3 = qta3;
	}

	public int getQta4() {
		return qta4;
	}

	public void setQta4(int qta4) {
		this.qta4 = qta4;
	}

	public int getQta5() {
		return qta5;
	}

	public void setQta5(int qta5) {
		this.qta5 = qta5;
	}

	@Override
	public String toString() {
		return "CartaDesc [id=" + id + ", nome=" + nome + ", rarita=" + rarita + ", condizione1=" + condizione1
				+ ", condizione2=" + condizione2 + ", condizione3=" + condizione3 + ", condizione4=" + condizione4
				+ ", condizione5=" + condizione5 + ", qta1=" + qta1 + ", qta2=" + qta2 + ", qta3=" + qta3 + ", qta4="
				+ qta4 + ", qta5=" + qta5 + "]";
	}
	
	

}
