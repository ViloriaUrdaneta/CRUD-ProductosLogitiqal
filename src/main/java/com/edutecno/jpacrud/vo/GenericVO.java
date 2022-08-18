package com.edutecno.jpacrud.vo;

//Clase genérica de visual object que agrega mensaje y código para las validaciones
public class GenericVO {

	//Atributos
	String mensaje;
	String codigo;
	
	//Constructor con atributos y vacio
	public GenericVO(String mensaje, String codigo) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
	}
	public GenericVO() {
		super();
	}
	
	//Getters y Setters
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	//ToString
	@Override
	public String toString() {
		return "GenericVO [mensaje=" + mensaje + ", codigo=" + codigo + "]";
	}
	
	
	
	
}
