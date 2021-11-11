package semillero.pruebasUnitarias;

import java.math.BigDecimal;
import java.time.LocalDate;
//import java.time.Month;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
//import org.hibernate.internal.StaticFilterAliasGenerator;
import org.testng.annotations.Test;
//import org.omg.CORBA.PRIVATE_MEMBER;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.EstadoEnum;
import com.hbt.semillero.entidad.TematicaEnum;

import junit.framework.Assert;

/**
 * 
 * <b>Descripción:<b> Clase que determina la creacion de un comic
 * <b>Caso de Uso:<b> 
 * @author eliana
 * @version
 */
public class CreacionComicTest {
	
	/**
	 * Constante que contendra el log de la clase AritmeticaTest
	 */
	private final static Logger log = Logger.getLogger(CreacionComicTest.class);
	
	/**
	 * atributo que determina la lista de comics
	 */
	public ArrayList<ComicDTO> comics = new ArrayList<ComicDTO>();
	
	
	/**
	 * atributo que determina la lista de comics activos
	 */
	public ArrayList<ComicDTO> comicsActivos;

	/**
	 * atributo que determina la lista de comics inactivos
	 */
	public ArrayList<ComicDTO> comicsInactivos;
	
	/**
	 * 
	 * Método encargado de pruebas
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 *
	 */
	
	@BeforeTest
	public void inicializar() {
		if(comics.isEmpty()){
			llenarListaComics();
		}
		this.comicsActivos = buscarActivos();
		this.comicsInactivos = buscarInactivos();
		
		BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
	
	/**
	 * 
	 * Metodo encargado de buscar los comics activos
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 * 
	 * @return
	 */
	private ArrayList<ComicDTO> buscarActivos(){
		
		ArrayList<ComicDTO> lstActivos = new ArrayList<ComicDTO>();
		
		for (ComicDTO comicDTO : comics) {
			if(comicDTO.getEstadoEnum()==EstadoEnum.ACTIVO) {
				lstActivos.add(comicDTO);
			}
		}
		return lstActivos;
	}
	
	/**
	 * 
	 * Metodo encargado de buscar los comics inactivos
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 * 
	 * @return
	 */
	private ArrayList<ComicDTO> buscarInactivos(){
		
		ArrayList<ComicDTO> lstInactivos = new ArrayList<ComicDTO>();
		
		for (ComicDTO comicDTO : comics) {
			if(comicDTO.getEstadoEnum()==EstadoEnum.INACTIVO) {
				lstInactivos.add(comicDTO);
			}
		}
		return lstInactivos;
	}
	
	/**
	 * 
	 * Metodo encargado de validar los comics activos
	 * <b>Caso de Uso semillero HBT</b>
	 * @author elian
	 *
	 */
	@Test
	public void validarActivos() {
		log.info("Se inicializa el método validarActivos");
		Assert.assertNotNull(comicsActivos);
		System.out.println(comicsActivos.size());
		for (ComicDTO comicDTO : comicsActivos) {
			if(comicDTO.getEstadoEnum()==EstadoEnum.ACTIVO) {
				System.out.println(comicDTO.toString());
			}
			
		}
		log.info("Se finaliza el método validarActivos");
	}
	/**
	 * 
	 * Metodo encargado de validar los comics inactivos
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 *
	 */
	@Test
	public void validarInactivos() {
		log.info("Se inicializa el método validarInactivos");
		Assert.assertNotNull(comicsInactivos);
		try {
				throw new Exception("Se ha detectado que de " + comics.size() + " comics se encontraron que " + comicsActivos.size() + " se encuentran activos y " + comicsInactivos.size() + " inactivos. Los comics inactivos son: " + comicsInactivos.toString());
				
		} catch (Exception e) {
			
			
			Assert.assertEquals(e.getMessage(), "Se ha detectado que de " + comics.size() + " comics se encontraron que " + comicsActivos.size() + " se encuentran activos y " + comicsInactivos.size() + " inactivos. Los comics inactivos son: " + comicsInactivos.toString());
			log.info(e);
		}
		log.info("Se finaliza el método validarInactivos");
	}
	
	/**
	 * 
	 * Metodo encargado de llenar la lista de comics
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 *
	 */
	
	private void llenarListaComics() {
		ComicDTO comic1 = new ComicDTO(new Long(1), "Thor", "marvel", TematicaEnum.CIENCIA_FICCION, "1", 10, new BigDecimal(300000), "autor1", true, LocalDate.of(2021, 03, 10), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic1);
		ComicDTO comic2 = new ComicDTO(new Long(2), "lol", "liga justicia", TematicaEnum.FANTASTICO, "2", 8, new BigDecimal(200000), "autor2", false, LocalDate.of(2021, 03, 20), EstadoEnum.INACTIVO,new Long(200));
		comics.add(comic2);
		ComicDTO comic3 = new ComicDTO(new Long(3), "MM", "marvel", TematicaEnum.AVENTURAS, "1", 5, new BigDecimal(350000), "autor3", true, LocalDate.of(1998, 03, 20), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic3);
		ComicDTO comic4 = new ComicDTO(new Long(4), "th", "loga justicia", TematicaEnum.HORROR, "2", 8, new BigDecimal(100000), "autor4", true, LocalDate.of(2010, 03, 10), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic4);
		ComicDTO comic5 = new ComicDTO(new Long(5), "SM", "marvel", TematicaEnum.FANTASTICO, "3", 10, new BigDecimal(180000), "autor5", true, LocalDate.of(2000, 03, 20), EstadoEnum.INACTIVO,new Long(200));
		comics.add(comic5);
		ComicDTO comic6 = new ComicDTO(new Long(6), "SH", "marvel", TematicaEnum.CIENCIA_FICCION, "7", 15, new BigDecimal(370000), "autor6", false, LocalDate.of(2018, 03, 10), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic6);
		ComicDTO comic7 = new ComicDTO(new Long(7), "VG", "loga justicia", TematicaEnum.HISTORICO, "1", 19, new BigDecimal(120000), "autor7", true, LocalDate.of(2015, 03, 17), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic7);
		ComicDTO comic8 = new ComicDTO(new Long(8), "LJ", "marvel", TematicaEnum.AVENTURAS, "1", 8, new BigDecimal(300000), "autor8", false, LocalDate.of(2003, 03, 20), EstadoEnum.INACTIVO,new Long(200));
		comics.add(comic8);
		ComicDTO comic9 = new ComicDTO(new Long(9), "OL", "marvel", TematicaEnum.CIENCIA_FICCION, "7", 10, new BigDecimal(170000), "autor9", true, LocalDate.of(2004, 03, 25), EstadoEnum.ACTIVO,new Long(200));
		comics.add(comic9);
		ComicDTO comic10 = new ComicDTO(new Long(10), "HJ", "liga justicia", TematicaEnum.BELICO, "10", 15, new BigDecimal(320000), "autor10", true, LocalDate.of(2021, 03, 18), EstadoEnum.INACTIVO,new Long(200));
		comics.add(comic10);
	}
	
	/**
	 * 
	 * Metodo encargado de finalizar las pruebas
	 * <b>Caso de Uso semillero HBT</b>
	 * @author eliana
	 *
	 */
	
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
	
	

	
	
	
}
