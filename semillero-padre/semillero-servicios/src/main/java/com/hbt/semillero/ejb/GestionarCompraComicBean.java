package com.hbt.semillero.ejb;

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

import com.hbt.semillero.dto.ConsultaCompraDTO;
import com.hbt.semillero.entidad.EstadoEnum;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComic{
	
	@PersistenceContext
	public EntityManager em;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ConsultaCompraDTO comprarComic(Long idComic,Long cantidadCompra){
		EstadoEnum estado = null;
		Long cantidad = null;
		Long cantidadTotal = nulll;
		
		//String consulta = "SELECT new com.hbt.semillero.dto.ConsultaCompraDTO(c.estadoEnum, c.cantidad)  "
		//		+ " FROM Comic c WHERE c.id = :idComic";
		
		ConsultaCompraDTO consultaCompraDTO = new ConsultaCompraDTO();
		
		String consultarEstado = " SELECT c.estadoEnum FROM Comic c WHERE c.id = :idComic";
		String consultarCantidad = " SELECT c.cantidad FROM Comic c WHERE c.id = :idComic";
		try {
			
			//Query consultaNativa = em.createQuery(consulta);
			//consultaNativa.setParameter("idComic", idComic);
			//consultaCompraDTO = (ConsultaCompraDTO) consultaNativa.getSingleResult();
			
			Query queryEstado = em.createQuery(consultarEstado);
			queryEstado.setParameter("idComic", idComic);
			estado = (EstadoEnum) queryEstado.getSingleResult();
			
			Query queryCantidad = em.createQuery(consultarCantidad);
			queryCantidad.setParameter("idComic", idComic);
			cantidad = (Long) queryCantidad.getSingleResult();
			
			if(estado != EstadoEnum.INACTIVO) {
				if(cantidad<=cantidadCompra) {
					String actualizarCantidad = " UPDATE Comic c SET c.cantidad = :stock WHERE c.id = :idComic";
					Query queryActualizar = em.createQuery(actualizarCantidad);
					cantidadTotal = cantidad -cantidadCompra;
					queryActualizar.setParameter("cantidad",cantidadTotal);
					queryActualizar.setParameter("idComic", idComic);
					queryActualizar.executeUpdate();
				}if(cantidad ==cantidadCompra) {
					String actualizarCantidad = " UPDATE Comic c SET c.cantidad = :stock WHERE c.id = :idComic";
					Query queryActualizar = em.createQuery(actualizarCantidad);
					cantidadTotal = cantidad -cantidadCompra;
					queryActualizar.setParameter("cantidad",cantidadTotal);
					queryActualizar.setParameter("idComic", idComic);
					queryActualizar.setParameter("estado", EstadoEnum.INACTIVO);
					queryActualizar.executeUpdate();
					
				}
				consultaCompraDTO.setMensajeEjecucion("La compra del comic" + idComic +"nombreComic fue exitosa");
			}
			
		} catch (Exception e) {
			consultaCompraDTO.setExitoso(false);
			consultaCompraDTO.setMensajeEjecucion("“El comic seleccionado no cuenta con stock en bodega");
		}
		
		
		return null;
	}

}
