package com.enrique.orm.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.enrique.orm.dbacces.ConnectionPool;
import com.enrique.orm.entity.Editorial;
import com.enrique.orm.entity.Libro;

/**Implementacion del Servicio de Libreria.
 * Podria separar la clase en una que maneje la conexion a la db y otra que implemente
 * metodos genericos CRUD.
 * Para simplificar solo son 2 queries que levantan las editoriales y los libros
 * Se define como Singleton para que siempre me inyecte la misma instancia
 * */

@AnnLibreriaService
@Singleton
public class LibreriaServiceImpl implements LibreriaService {
	private ConnectionPool cp;

	/** 
	 * Realida la conexion conta la DB, en base al String de conexion, el usuario y la pass
	 * */
	public void connectToDB(String connstring, String dbuser, String dbpass) {
		if (cp == null) {
			try {
				cp = new ConnectionPool(connstring, dbuser, dbpass);
				System.out.println("Conexion realizada con exito a :" + connstring);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Ya conectado con " + connstring);

		}
	}

	/**Metod que devuelve la lista de editoriales 
	 * 
	 * */
	public List<Editorial> getAllEditoriales() {
		List<Editorial> listaEditoriales = new ArrayList<>();
		StringBuilder sbEditorial = new StringBuilder();
		//query
		sbEditorial.append("SELECT * FROM epitest.Editorial;");

		Connection con;
		try {
			con = cp.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rsEditoriales = stmt.executeQuery(sbEditorial.toString());
			
			if (rsEditoriales != null) {
				// Para poder mapear el resultado a cada editorial us el resulsetmaper
				MapeadorResultSet<Editorial> resultSetMapperEditorial = new MapeadorResultSet<Editorial>();
				// La list Obtiene como resultado una lista de objetos mapeados al la clase que se le pasa!
				listaEditoriales = resultSetMapperEditorial.mapRersultSetToObject(rsEditoriales, Editorial.class);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEditoriales;
	}

	/**Similar al de editoriales, solo que hace el query de la editorial seleccionada.
	 * */
	@Override
	public List<Libro> getLibrosxEditorial(String action) {
		List<Libro> listaLibros = new ArrayList<>();
		StringBuilder sbLibros = new StringBuilder();
		sbLibros.append("SELECT * FROM epitest.Libro where editorial = ");
		sbLibros.append(action);
		Connection con;
		try {
			con = cp.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rsLibros = stmt.executeQuery(sbLibros.toString());
			if (rsLibros != null) {
				// Para poder mapear el resultset a Libros
				MapeadorResultSet<Libro> resultSetMapperLibros = new MapeadorResultSet<Libro>();
				listaLibros = resultSetMapperLibros.mapRersultSetToObject(rsLibros, Libro.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaLibros;
	}

}
