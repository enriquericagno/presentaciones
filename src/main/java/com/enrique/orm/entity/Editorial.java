package com.enrique.orm.entity;

import java.util.List;

@AnnTablaER(nombreTabla = "Editorial")
public class Editorial {

	@AnnColumnaER(nombreColumna = "idEditorial")
	private int idEditorial;
	
	private List<Libro> libros;
	
	@AnnColumnaER(nombreColumna = "nombre")
	private String nombre;
	
	@AnnColumnaER(nombreColumna = "direccion")
	private String direccion;

	public Editorial() {
		super();
	}

	public Editorial(int idEditorial, String nombre, String direccion) {
		super();
		this.idEditorial = idEditorial;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Editorial [idEditorial=" + idEditorial  + ", nombre=" + nombre + ", direccion="
				+ direccion+ ", libros=" + libros + "]";
	}

	

}
