package com.enrique.orm.entity;

import java.util.Date;

@AnnTablaER(nombreTabla = "Libro")
public class Libro {

	@AnnColumnaER(nombreColumna = "idLibro")
	private int idLibro;
	
	@AnnColumnaER(nombreColumna = "nombre")
	private String nombre;
	
	@AnnColumnaER(nombreColumna = "nombrelargo")
	private String nombrelargo;
	
	@AnnColumnaER(nombreColumna = "descripcion")
	private String descripcion;
	
	@AnnColumnaER(nombreColumna = "autor")
	private String autor;
	
	@AnnColumnaER(nombreColumna = "fechaedicion")
	private Date fechaedicion;
	
	@AnnColumnaER(nombreColumna = "editorial")
	private int entero;
//	private Editorial editorial;
	
	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(int idLibro, String nombre, String nombrelargo, String descripcion, String autor, Date fechaedicion,
		int entero) {
	super();
	this.idLibro = idLibro;
	this.nombre = nombre;
	this.nombrelargo = nombrelargo;
	this.descripcion = descripcion;
	this.autor = autor;
	this.fechaedicion = fechaedicion;
	this.entero = entero;
}

	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombrelargo() {
		return nombrelargo;
	}
	public void setNombrelargo(String nombrelargo) {
		this.nombrelargo = nombrelargo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getFechaedicion() {
		return fechaedicion;
	}
	public void setFechaedicion(Date fechaedicion) {
		this.fechaedicion = fechaedicion;
	}

	public int getEntero() {
		return entero;
	}

	public void setEntero(int entero) {
		this.entero = entero;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", nombre=" + nombre + ", nombrelargo=" + nombrelargo + ", descripcion="
				+ descripcion + ", autor=" + autor + ", fechaedicion=" + fechaedicion + ", entero=" + entero + "]";
	}
	
	

	
	
	
	
}
