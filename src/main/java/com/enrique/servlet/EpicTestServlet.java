package com.enrique.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enrique.orm.entity.Editorial;
import com.enrique.orm.services.AnnLibreriaService;
import com.enrique.orm.services.LibreriaService;

@WebServlet(name = "testServelet", urlPatterns = { "/testServelet" }, loadOnStartup = 1)
public class EpicTestServlet extends HttpServlet {

	//equicalente al autowired y al qualifier de Spring
	@Inject 
	@AnnLibreriaService
	private LibreriaService libreriaService2;

	private static Locale[] supportedLocales = { new Locale("es", "AR"), new Locale("en", "US") };
	private static Locale currentLocale = supportedLocales[0];
	private static ResourceBundle configs = ResourceBundle.getBundle("com.enrique.properties", currentLocale);

	private static String connstring;
	private static String dbuser;
	private static String dbpass;

	private static final long serialVersionUID = 1L;

	//contructor que levanta la configuracion
	public EpicTestServlet() {
		System.out.println("**** ClientServlet initializing");
		connstring = configs.getString("connstring");
		dbuser = configs.getString("dbuser");
		dbpass = configs.getString("dbpass");		
	}

	//una vez que se realizo la inyeccion del bean, puedo usarlo,
	//asi que inicializo la coneccion con la db
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		libreriaService2.connectToDB(connstring, dbuser, dbpass);
	}

	//manejo la peticion de la pag.
	//inserto la lista como atributo para que este diponible y mostrar los datos
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		List<Editorial> lista = libreriaService2.getAllEditoriales();
		req.setAttribute("lista", lista);
		System.out.println( " Libreria 2 " + libreriaService2);
		resp.setContentType("text/html;charset=UTF-8");
		req.getRequestDispatcher("/lista-editoriales.jsp").forward(req, resp);
	}
	
	


}
