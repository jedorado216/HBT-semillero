package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.entidad.Comic;

@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;
	/**
	 * 
	 * Método encargado de consultar Nombre y precio de un comic
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param idComic
	 * @return
	 */
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}
	
	/**
	 * 
	 * Método encargado de crear un comic
	 * <b>Caso de Uso semillero HBT</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param comicDTO
	 * @return ComicDTOResult
	 */
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	}
	/**
	 * 
	 * Método encargado de consultar el tamaño del atributo nombre del comic
	 * <b>Caso de Uso semillero HBT</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param lengthComic
	 * @return tamaño del nombre del comic
	 */
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre(@QueryParam("lengthComic") short lengthComic) {
		
		return this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);
	}
	
	/**
	 * 
	 * Método encargado de actualizar un comic
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param comicDTO
	 * @return comicDTOResult
	 */
	@POST
	@Path("/actualizarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.actualizarComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	}
	/**
	 * 
	 * Método encargado de eliminar un comic
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param idComic
	 * @return
	 */
	@POST
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO eliminarComic(Long idComic) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.eliminarComic(idComic);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	}
	/**
	 * 
	 * Método encargado de consultar todos los comics
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param lengthComic
	 * @return
	 */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComics() {
		
		return this.gestionarComicLocal.consultarComics();
	}
	/**
	 * 
	 * Método encargado de consultar un Comic
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu
	
	 * 
	 * @param comicDTO
	 * @return
	 */
	@GET
	@Path("/consultarUnComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarUnComic(@QueryParam("idComic") Long idComic) {
		
		return this.gestionarComicLocal.consultarUnComic(idComic);	
	}
	
	
	
	
}
