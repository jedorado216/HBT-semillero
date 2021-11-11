package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
//import com.hbt.semillero.entidad.EstadoEnum;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;
	
	/**
	 * método consulta Nombre y Precio de un Comic por idComic
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarNombrePrecioComic(java.lang.Long)
	 */

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
						+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaNombrePrecioDTO;
	}
	/**
	 * método que crea un comic y retorna un comicDTOResult
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		
		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}
	
	/**
	 * Método encargado de  eliminar un comic
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ComicDTO eliminarComic(Long idComic) {
		ComicDTO comicDTOResultado = new ComicDTO();
		String eliminarComic = " DELETE FROM Comic WHERE id = :idComic";
		try {
			comicDTOResultado = consultarUnComic(idComic);
			Query queryEliminar = em.createQuery(eliminarComic);
			queryEliminar.setParameter("idComic", idComic);
			queryEliminar.executeUpdate();
			comicDTOResultado.setExitoso(true);
			comicDTOResultado.setMensajeEjecucion("Comic eliminado exitosamente");
			
		} catch (NoResultException e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("No existe comic con ese id");
		} catch (Exception e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}
		
		return comicDTOResultado;
	}
	/**
	 * Método que consulta todos los comic
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */

	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		
		String findAllComic = " SELECT cm FROM Comic cm ";
		//ComicDTO ComicDTO = new ComicDTO();
		
		//List<ComicDTO> lista = new ArrayList<ComicDTO>();
		
		/*try {
			Query queryFindAllComic = em.createQuery(findAllComic);
			List<ComicDTO> listaComics = queryFindAllComic.getResultList();
			for (ComicDTO comicDTO : listaComics) {
				comicDTO.setExitoso(true);
				comicDTO.setMensajeEjecucion("se ejecuto exitosamente la consulta");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		
		Query queryFindAllComic = em.createQuery(findAllComic);
		List<ComicDTO> listaComics = queryFindAllComic.getResultList();
		return listaComics;//postman
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
	
	/**
	 * Método que retorna los nombres de los comics filtrando por tamaño del nombre
	 *  @param lengthComic
	 * @return ConsultarComicTamanioNombreDTO
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComicTamanioNombre(short)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre(short lengthComic) {
		
		ConsultarComicTamanioNombreDTO consultarComicTamanioNombreDTO = new ConsultarComicTamanioNombreDTO();
		
		try {
			String con = " SELECT c.nombre FROM Comic cm ";
			Query queryconsultaComics= em.createQuery(con);
			List<String> listaComics = queryconsultaComics.getResultList();
			if(lengthComic >100) {
				throw new Exception("La longitud máxima permitida es de 100 caracteres");
			}
			
			for (String nombreComic : listaComics) {
				if(nombreComic.length() >= lengthComic){
					consultarComicTamanioNombreDTO.getNombresMayor().add(nombreComic);
				}else {
					consultarComicTamanioNombreDTO.getNombresMenor().add(nombreComic);
				}	
			}
			consultarComicTamanioNombreDTO.setExitoso(true);
			consultarComicTamanioNombreDTO.setMensajeEjecucion("Comics procesados exitosamente");
			
		} catch (Exception e) {
			
			consultarComicTamanioNombreDTO.setExitoso(false);
			consultarComicTamanioNombreDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}
		return consultarComicTamanioNombreDTO;
	}
	/**
	 * Método encargado de acutalizar un comic(todos los parámetros)
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#actualizarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO actualizarComic(ComicDTO comicDTO){
		ComicDTO comicDTOResultado = new ComicDTO();
		try {
			if(consultarUnComic(comicDTO.getId()) != null) {
				em.merge(convertirComicDTOToComic(comicDTO));
				comicDTOResultado = comicDTO;
			}
			comicDTOResultado.setExitoso(true);
			comicDTOResultado.setMensajeEjecucion("Se actualizo con exito el comic.");
		} catch (NoResultException e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("No existe comic con ese id.");
		} catch (Exception e) {
			comicDTOResultado.setExitoso(false);
			comicDTOResultado.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic" + e.getMessage());
		}
		return comicDTOResultado;
	}
	/**
	 * 
	 * Método encargado de consultar un Comic de la lista de comic
	 * <b>Caso de Uso</b>
	 * @author Jhulieth Eliana Dorado-jedorado216@unicauca.edu.co
	
	 * 
	 * @param idComic
	 * @return comic
	 */

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarUnComic(Long idComic){
		ComicDTO comicDTOResultado = new ComicDTO();
		Comic comic = null;
		try {
			String comicId = " SELECT c FROM Comic c WHERE c.id = :idComic ";
			Query queryUnComicConParametro = em.createQuery(comicId);
			queryUnComicConParametro.setParameter("idComic", idComic);
			comic = (Comic) queryUnComicConParametro.getSingleResult();
			comicDTOResultado = convertirComicToComicDTO(comic);
		}catch (NoResultException nre) {
			
		}catch (Exception e) {
		}
		
		return comicDTOResultado;
	}
	
	

}
