package com.hbt.semillero.dto;

import java.util.ArrayList;

public class ConsultarComicTamanioNombreDTO extends ResultadoDTO{
	
	/**
	 * clase que determina
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que determina el nombre de los comic mayor al parámetro
	 */
	public ArrayList<String> nombresMayor = new ArrayList<String>();
	/**
	 *Atributo que determina el nombre de los comic menor al parámetro
	 */
	public ArrayList<String> nombresMenor = new ArrayList<String>();
	
	public ConsultarComicTamanioNombreDTO() {
		this.nombresMayor = new ArrayList<>();
		this.nombresMenor = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> getNombresMayor() {
		return nombresMayor;
	}

	public void setNombresMayor(ArrayList<String> nombresMayor) {
		this.nombresMayor = nombresMayor;
	}
	public ArrayList<String> getNombresMenor() {
		return nombresMenor;
	}
	public void setNombresMenor(ArrayList<String> nombresMenor) {
		this.nombresMenor = nombresMenor;
	}
	
	

	
}
