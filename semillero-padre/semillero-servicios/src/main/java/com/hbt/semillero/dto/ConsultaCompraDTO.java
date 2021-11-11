package com.hbt.semillero.dto;

import java.math.BigDecimal;

import com.hbt.semillero.entidad.EstadoEnum;

public class ConsultaCompraDTO extends ResultadoDTO {

	/**
	 * Atributo que determina  
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * atributo que determina el estado de un comic
	 */
	private EstadoEnum estadoEnum;
	/**
	 * atributo que determina la cantidad de comic
	 */
	private Long cantidad;
	
	public ConsultaCompraDTO(EstadoEnum estadoEnum, Long cantidad) {
		this.estadoEnum = estadoEnum;
		this.cantidad = cantidad;
	}
	public ConsultaCompraDTO() {

	}

	/**
	 * 
	 * Método encargado de retornar estad
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @return
	 */
	public EstadoEnum getEstado() {
		return estadoEnum;
	}
	/**
	 * 
	 * Método encargado de enviar estado
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoEnum estado) {
		this.estadoEnum = estado;
	}
	
	/**
	 * 
	 * Método encargado de obtener cantidad
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @return
	 */
	public Long getCantidad() {
		return cantidad;
	}
	/**
	 * 
	 * Método encargado de recibir cantidad
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param cantidad
	 */
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
	
	

}
