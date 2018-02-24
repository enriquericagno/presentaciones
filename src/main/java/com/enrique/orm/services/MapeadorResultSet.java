package com.enrique.orm.services;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.enrique.orm.entity.AnnColumnaER;
import com.enrique.orm.entity.AnnTablaER;


/**Mapeador de Resultset.
 * Trabaja en generico, devolviendo una lista de objetos de acuerdo a la clase pasada
 * */
public class MapeadorResultSet<T> {
	@SuppressWarnings("unchecked")
	public List<T> mapRersultSetToObject(ResultSet rs, @SuppressWarnings("rawtypes") Class outputClass) {
		List<T> outputList = null;
		try {
			// si hay algun resultado
			if (rs != null) {
				// Primero valido que la clase pasada para modelar, sea del tipo entity (en mi caso con el annotation que cree)
				if (outputClass.isAnnotationPresent(AnnTablaER.class)) {
					// Obtengo la Metadata del resulset
					ResultSetMetaData rsmd = rs.getMetaData();
					// y los attributos de la clase de modelado
					Field[] camposDeclarados = outputClass.getDeclaredFields();
					while (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 1; _iterator <= rsmd
								.getColumnCount(); _iterator++) {
							// Tomo los nombres de las columnas de la Metadata que viene de la query directamente
							String nombreDeLaColumna = rsmd
									.getColumnName(_iterator);
							// leo el valor de la columna
							Object valorDeLaColumna = rs.getObject(_iterator);
							// Ahora es momento de comparar el nobre de la columna con el nombre de los campos pasados
							//si son iguales asignarlos
							for (Field campo : camposDeclarados) {
								if (campo.isAnnotationPresent(AnnColumnaER.class)) {
									AnnColumnaER columnaAnnotada = campo
											.getAnnotation(AnnColumnaER.class);
									if (columnaAnnotada.nombreColumna().equalsIgnoreCase(
											nombreDeLaColumna)
											&& valorDeLaColumna != null) {
										BeanUtils.setProperty(bean, campo
												.getName(), valorDeLaColumna);
										break;
									}
								}
							}
						}
						if (outputList == null) {
							outputList = new ArrayList<T>();
						}
						outputList.add(bean);
					}

				} else {
					System.out.println("error rs es nulo");
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return outputList;
	}
}