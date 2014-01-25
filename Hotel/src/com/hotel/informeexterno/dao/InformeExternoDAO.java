/**
 * InformeExternoDAO 29/07/2013
 */
package com.hotel.informeexterno.dao;

import java.util.List;

import com.hotel.informeexterno.bean.InformeExternoDTO;
import com.hotel.informeexterno.bean.InformeExternoDetalleDTO;

/**
 * @author Gustavo A. Correa C.
 *
 */
public interface InformeExternoDAO {

	Object registrarIE(InformeExternoDTO ie, List<InformeExternoDetalleDTO> ieDet) throws Exception;
	List<InformeExternoDTO> buscaInformeExterno(InformeExternoDTO ie) throws Exception;
}
