package com.hbt.semillero.ejb;

import javax.ejb.Local;

import com.hbt.semillero.dto.ConsultaCompraDTO;

@Local
public interface IGestionarCompraComic {
	
	public ConsultaCompraDTO comprarComic(Long idComic,Long cantidadCompra);

}
