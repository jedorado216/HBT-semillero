/**
 * EstadoEnum.java
 */
package com.hbt.semillero.entidad;


/**
 * 
 * <b>Descripción:<b> Clase que determina la enumeración para representar los tipos de estado 
 * <b>Caso de Uso: Semilero HBT<b> 
 * @author eliana
 * @version
 */
public enum EstadoEnum {
	/**
	 * estados del estado enumk
	 */
	ACTIVO("enum.estado.activo"),
	INACTIVO("enum.tematica.aventuras");
	
	/**
	 * Atributo para la internacionalizacion
	 */
	private String descripcion;
	
	/**
	 * 
	 * Constructor de la clase que recibe como parametro la descripcion.
	 * @param descripcion
	 */
	EstadoEnum(String descripcion) {
		this.setDescripcion(descripcion);
	}

	/**
	 * Metodo encargado de retornar el valor del atributo descripcion
	 * @return El descripcion asociado a la clase
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo descripcion
	 * @param descripcion El nuevo descripcion a modificar.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
			
			
	
}
