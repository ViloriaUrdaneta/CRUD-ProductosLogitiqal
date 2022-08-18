package com.edutecno.jpacrud.vo;

public class NumberVO extends GenericVO {

	private long valor;

	
	public NumberVO() {
		super();
	}
	public NumberVO(String mensaje, String codigo) {
		super(mensaje, codigo);
	}
	public NumberVO(long valor, String mensaje, String codigo) {
		super(mensaje, codigo);
		this.valor = valor;
	}
	
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
	}
	
}
