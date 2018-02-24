package com.enrique.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enrique.orm.entity.Libro;
import com.enrique.orm.services.AnnLibreriaService;
import com.enrique.orm.services.LibreriaService;


/**Maneja la peticion de los libros. Toma el parametro pasado por url como editorialId, para poder mostrar
 * Los libros de dicha editorial
 * */
@WebServlet(urlPatterns = { "/librosxEditorial" })
public class MuestraLibrosServlet extends HttpServlet {

	//inyecto el mismo bean para poder acceder a la db
	@Inject
	@AnnLibreriaService
	private LibreriaService libreriaConectada;
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("editorialId");
		System.out.println( " Libreria 2 " + libreriaConectada);
		List<Libro> listaLibros = libreriaConectada.getLibrosxEditorial(action);
		//seteo el atributo que me permite acceder a los datos en la pagina
		req.setAttribute("listalibros", listaLibros);
		resp.setContentType("text/html;charset=UTF-8");
		req.getRequestDispatcher("/lista-libros.jsp").forward(req, resp);
	}

}