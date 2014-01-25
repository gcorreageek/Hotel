/**
 * InformeInternoDAO 29/07/2013
 */
package com.hotel.informeinterno.dao;

import java.util.List;

import com.hotel.informeinterno.bean.InformeInternoDTO;
import com.hotel.informeinterno.bean.InformeInternoDetalleDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface InformeInternoDAO {

	Object registrarII(InformeInternoDTO ii, List<InformeInternoDetalleDTO> iiDet) throws Exception;
	
	List<InformeInternoDTO> buscaInformeInterno(InformeInternoDTO ii) throws Exception; 
	
}
