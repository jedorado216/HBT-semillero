package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
//import com.hbt.semillero.dto.ResultadoDTO;
//import com.hbt.semillero.entidad.Comic;

@Local
public interface IGestionarComicLocal {
	
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);

	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	public ComicDTO eliminarComic(Long idComic);
	
	public List<ComicDTO> consultarComics();
	
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre(short lengthComic);

	public ComicDTO actualizarComic(ComicDTO comicDTO);
	
	public ComicDTO consultarUnComic(Long idComic);
	
}