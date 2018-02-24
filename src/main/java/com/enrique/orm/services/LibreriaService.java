package com.enrique.orm.services;

import java.util.List;

import com.enrique.orm.entity.Editorial;
import com.enrique.orm.entity.Libro;

public interface LibreriaService {
	
 public void connectToDB(String connstring, String dbuser, String dbpass);
 public List<Editorial> getAllEditoriales();
 public List<Libro> getLibrosxEditorial(String action);
}
