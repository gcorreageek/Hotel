/**
 * Prueba 26/07/2013
 */
package com.hotel.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.Connection;


/**
 * @author Gustavo A. Correa C.
 *
 */
public class Prueba {

	private static Log log = LogFactory.getLog(Prueba.class);
	public static void main(String[] args) {
		 log.info("Testing Info Message.");
	        if (log.isDebugEnabled()) {
	            log.debug("Testing Debug Message.");
	        } 
	        Connection conexion = null;
	        
	        try {
				Class.forName("com.mysql.jdbc.Driver");
				 conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sigal", "root", "mysql");  
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
//	        try {
//				JasperReport reporte = (JasperReport) JRLoader.loadObject("report2.jasper");
//				JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
//				JRExporter exporter = new JRPdfExporter();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint); 
//				exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("reportePDF.pdf"));
//				exporter.exportReport();
//			} catch (JRException e) { 
//				e.printStackTrace();
//			}
	        
	        

//	        response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
//	        response.setHeader("Cache-Control", "no-cache");
//	        response.setHeader("Pragma", "no-cache");
//	        response.setDateHeader("Expires", 0); 
//	        response.setContentType("application/pdf"); 
//	        ServletOutputStream out = response.getOutputStream(); 
//	        List listaPariticipantes = new ArrayList(); 
//	        try
//	        {
//	            JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("WEB-INF/reporte2.jasper"));
//
//	            Map parametros = new HashMap();
//	            parametros.put("autor", "Juan");
//	            parametros.put("titulo", "Reporte");
//
//	            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaPariticipantes));
//
//	            JRExporter exporter = new JRPdfExporter();
//	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
//	            exporter.exportReport();
//	        }
//	        catch (Exception e)
//	            {
//	            e.printStackTrace();
//	        }
	        
	        
	        

//	        JasperReport reporte;
//			try {
//				reporte = (JasperReport) JRLoader.loadObject("rpt_productos5.jasper");
//				Map<String, Object> parametros = new HashMap<String, Object>();
//		        parametros.put("PRODUCTO", "");
//		        parametros.put("UMEDIDA", ""); 
//		        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,conexion );
//		        JRExporter exporter = new JRPdfExporter();
//		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
////	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
//		        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte3PDF.pdf"));
//		        exporter.exportReport();
//			} catch (JRException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

	       
	        
	        log.info("Paso x aqui!");
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
//		UtilSigal.fechaActual();
//		 SimpleDateFormat formateador = new SimpleDateFormat( "dd/MM/yyyy", new Locale("es_ES"));
//				   Date fechaDate = new Date();
//				   String fecha = formateador.format(fechaDate); 
//				   
//				   
//				   
//				   try
//			        {
//			            // Propiedades de la conexi�n
//			            Properties props = new Properties();
//			            props.setProperty("mail.smtp.host", "smtp.gmail.com");
//			            props.setProperty("mail.smtp.starttls.enable", "true");
//			            props.setProperty("mail.smtp.port", "465");
//			            props.setProperty("mail.smtp.user", "edinson@gmail.com");
//			            props.setProperty("mail.smtp.auth", "true");
//
//			            // Preparamos la sesion
//			            Session session = Session.getDefaultInstance(props);
//
//			            // Construimos el mensaje
//			            MimeMessage message = new MimeMessage(session);
//			            message.setFrom(new InternetAddress("edinson@gmail.com"));
//			            message.addRecipient(
//			                Message.RecipientType.TO,
//			                new InternetAddress("edinson@gmail.com"));
//			            message.setSubject("Hola");
//			            message.setText(
//			                "Mensajito con Java Mail" + "de los buenos." + "poque si");
//
//			            // Lo enviamos.
//			            Transport t = session.getTransport("smtp");
//			            t.connect("edinson@gmail.com", "d");
//			            t.sendMessage(message, message.getAllRecipients());
//
//			            // Cierre.
//			            t.close();
//			        }
//			        catch (Exception e)
//			        {
//			            e.printStackTrace();
//			        }
				   
///////////////////////////////////
	   // Create the email message
//		HtmlEmail email = new HtmlEmail();
//		email.setHostName("smtp.gmail.com");
//		email.setSmtpPort(587);
//		email.setAuthentication("edinson", "d");
//		email.setStartTLSEnabled(true);
//		email.setStartTLSRequired(true);
////		email.setAuthentication("edinson", "d");
//		
////		 props.setProperty("mail.smtp.starttls.enable", "true");
////         props.setProperty("mail.smtp.port", "587");
////         props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
////         props.setProperty("mail.smtp.auth", "true");
//		
//		try {
//			email.addTo("gcorreacaja@facebook.com", "Correa Facebbok");
//			email.setFrom("edinson@gmail.com", "GusiGuz");
//			email.setSubject("Asunto!");
//			// embed the image and get the content id
//			URL url = null;
//			url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//			String cid = null;
//			cid = email.embed(url, "Apache logo");
//			// set the html message
//			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");
//			// set the alternative message
//			email.setTextMsg("Your email client does not support HTML messages");
//			// send the email
//			email.send();
//		} catch (Exception e) { 
//			log.error("error",e); 
//		}	   
//				   
				   
				   
				   
	}
}
