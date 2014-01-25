package com.hotel.informeexterno.service;

import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.DAOFactory;
import com.hotel.informeexterno.bean.InformeExternoDTO;
import com.hotel.informeexterno.bean.InformeExternoDetalleDTO;
import com.hotel.informeexterno.dao.InformeExternoDAO;
import com.hotel.util.Constantes;

public class InformeExternoService {

	
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	InformeExternoDAO objInformeExternoDAO = fabrica.getInformeExternoDAO();
	
	public Object registrarOrdenCompra(InformeExternoDTO objii, List<InformeExternoDetalleDTO> lstIIDet) throws Exception {
		return objInformeExternoDAO.registrarIE(objii, lstIIDet);
	} 
	
	public List<InformeExternoDTO> lista(InformeExternoDTO objie ) throws Exception { 
	List<InformeExternoDTO> informeExternoLista = new ArrayList<InformeExternoDTO>();
	List<InformeExternoDTO> lst = objInformeExternoDAO.buscaInformeExterno(objie);
	int t = lst.size(); 
	int inicial = 0;
	List<InformeExternoDetalleDTO> detalle = null;
	for (int i = 0; i < t; i++) {
		InformeExternoDTO ii = lst.get(i);
		if(inicial==0){ 
			detalle = new ArrayList<InformeExternoDetalleDTO>();
		}
		inicial=1;
		InformeExternoDetalleDTO iiDetalle = new InformeExternoDetalleDTO();
		iiDetalle.setDesc_articulo(ii.getDesc_articulo());
		iiDetalle.setUnidadMedida(ii.getUnidadMedida());
		iiDetalle.setCantidad(ii.getCantidad()); 
		iiDetalle.setRaz_social(ii.getRaz_social());
		detalle.add(iiDetalle); 
		if(i+1<t){//Entra si hay un registro siguiente
			Integer cod = ii.getCod_informe_externo();
			Integer codSiguiente = lst.get(i+1).getCod_informe_externo(); 
			if(cod!=codSiguiente){ 
				inicial = 0;
				ii.setDetalle(detalle);
				informeExternoLista.add(ii);
			}  
		}else{//Este es el ultimo registro
			inicial = 0;
			ii.setDetalle(detalle);
			informeExternoLista.add(ii);
		} 
	} 
	return informeExternoLista;
}


	public List<InformeExternoDetalleDTO> getDetalle(InformeExternoDTO objii ) throws Exception {
		 List<InformeExternoDetalleDTO> lstDetalle = new ArrayList<InformeExternoDetalleDTO>();
		 List<InformeExternoDTO> lst = objInformeExternoDAO.buscaInformeExterno(objii);
		 for (InformeExternoDTO informeExternoDTO : lst) {
			 InformeExternoDetalleDTO det = new InformeExternoDetalleDTO();
			 det.setCantidad(informeExternoDTO.getCantidad());
			 det.setDesc_articulo(informeExternoDTO.getDesc_articulo());
			 det.setUnidadMedida(informeExternoDTO.getUnidadMedida());
			 det.setRaz_social(informeExternoDTO.getRaz_social());
			 lstDetalle.add(det);
		 } 
		return lstDetalle;
	} 
}
