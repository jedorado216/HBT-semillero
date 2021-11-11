package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.ejb.IGestionarCompraComic;


@Path("/gestionarCompraComic")
public class GestionarCompraComicRest {
	

	@EJB
	private IGestionarCompraComic gestionarcompraComic;
	
	@POST
	@Path("/comprarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(Long idComic,Long cantidadCompra) {
		ComicDTO comicDTOResult = new ComicDTO();
		return null;
	}

}
