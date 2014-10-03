/*
 * 
 *    ___________________________________________________________________
 *   |            COPYRIGHT (C) BY                                         |
 *   |            DERECHOS RESERVADOS (C) POR                              |
 *   |            LOBO SOFTWARE S.A. DE  C.V.                            |
 *   |                                                                   |
 *   | Ninguna parte de esta obra, parcial o total puede                 |
 *   | ser reproducida o transmitida, mediante ningún sistema            |
 *   | o método electrónico o mecánico (incluyendo el                    |
 *   | fotocopiado, la grabación, o cualquier sistema de                 |
 *   | recuperación y almacenamiento de información),                    |
 *   | SIN LA AUTORIZACION POR ESCRITO DEL AUTOR.                        |
 *   |                                                                   |
 *   | Derechos reservados                                               |
 *   | (C) 2012, LOBO SOFTWARE, S.A. DE C.V.                               |
 *   |                             (*)                                   |
 *   | Esta obra forma parte del SIAL-CH (C) "Capital Humano"              |
 *   |                                                                   |
 *   | (*) Marca registrada por                                          |
 *   | LOBO SOFTWARE, S.A. DE C.V.                                       |
 *   |___________________________________________________________________|
 *   
 *   Document   : MsgError
 *   Created on : Sep 27, 2012, 11:12:11 AM
 *   Author     : MFOB
 *   Modificaciónes:
 *   27/Ago/2013 JCOC Se modificó que se obtenga el campo real para las infracciones de unique en oracle
 *   04/Nov/2013 SAACH Se agrego el mensaje de error "Los datos de la factura actual, no cumplen con los requisitos fiscales"
 *   20/Ene/2014 JCOC Mostraba mensaje incorrecto cuando existe error de arrayindex y null pointer
	 14/May/2014 JCOC Se agrego el manejo de errores tipo numberFormatException
30/Jun/2014 10:44 JCOC Se cambió la expresión regular para cuando se insertan datos duplicados
 */
package mx.com.lobos.ex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.log4j.PropertyConfigurator;


/**
 *
 * @author marioo
 * @modificaciones: FESG Se obtimiza el bucle y se agrega la validacion para cuando ocurra un null pointer exception.
 */
public class MsgError {
//    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MsgError.class);
/**
 * 
 * @param ex !importante
 * @param strMetodo !importante
 * @return 
 */
    public String getMsgError(Throwable ex, String strMetodo){
        String strMensaje = "",strMensajeUsuario = "",temp;
        StringBuilder       sb = null;
        try {
//            PropertyConfigurator.configure("log4j.properties");
                  sb                  = new StringBuilder();
            StackTraceElement[] ste                 = ex.getStackTrace();
            strMensaje          = ex.getMessage() != null ? ex.getMessage().toString().replace("\"","").replace(System.getProperty("line.separator"),"<br>").replace("\n","<br>").replace("Error Code:","<b><u>Error Code:</u></b>").replace("\t","") :"Null pointer Exception";
            if(strMensaje.contains("userMessage")){
                int posUserMessage = strMensaje.lastIndexOf("userMessage");
                strMensajeUsuario = strMensaje.substring(posUserMessage+12);
                strMensaje = strMensaje.replace("userMessage:"+strMensajeUsuario,"");
            }else{
                strMensajeUsuario = MsgError.getMensajeErrorUsuario(ex.getMessage());
                if(ex.getMessage()==null){
                    strMensaje = ex.toString();
                }
            }
            for(int i = 0, total =  ste.length; i < total; i++){
                if(strMetodo.equals(ste[i].getMethodName())){
                    sb.append("<b>Clase&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b> ").append(    ste[i].getClassName()  ).append(",").append("<br>");
                    sb.append("<b>M&eacute;todo&nbsp;&nbsp;:</b> ").append( ste[i].getMethodName()  ).append(",").append("<br>");
                    sb.append("<b>Linea&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b> ").append(  ste[i].getLineNumber()  ).append(",").append("<br>");
                    sb.append("<b>Causa&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b> ").append(  strMensaje              ).append("<br>");
                    break;
                }
            }
//            log.error(sb.toString());
            
        } catch (Exception ex1) {
            Logger.getLogger(MsgError.class.getName()).log(Level.SEVERE, null, ex1);
        } 
        if(strMensaje.equals("java.lang.NullPointerException")||
                strMensaje.equals("java.lang.ArrayIndexOutOfBoundsException")||
                strMensaje.equals("java.lang.NumberFormatException")){
            return sb.toString()+"\",\"userMessage\":\"Error no identificado";
        }else{
            return sb.toString()+"\",\"userMessage\":\""+strMensajeUsuario+"";
        }
    }
    
    public String getMsgErrorLog(Throwable ex, String strMetodo){
//        PropertyConfigurator.configure("log4j.properties");
        String strMensaje = ex.getMessage().replace("\"", "").replace("\n", " ");
//        log.error(strMensaje);
        return strMensaje;
    }
    
    public static String getMensajeErrorUsuario(String e){
        String mensajeError = "Error no identificado";
        
            if(e!=null){
        
                String mensaje = e.toString().replace(System.getProperty("line.separator"),"<br>");

                if(mensaje.contains("Error Code:")){
                    int posicionInicial = mensaje.indexOf("Error Code:")+12;
                    int posicionFinal = mensaje.indexOf("<br>",posicionInicial);
                    int errorCode = Integer.parseInt(mensaje.toString().substring(posicionInicial, posicionFinal));

    //                String driver = Conexion.getEntityManagerFactory(parametrosHsm).getProperties().get("javax.persistence.jdbc.driver").toString();
                    String driver = "oracle.jdbc.OracleDriver";

                    if(driver.contains("OracleDriver")){
                        switch(errorCode){
                            case 1:
                                Pattern pattern = Pattern.compile("(\\_)([A-Z\\_]*)(\\))");
                                Matcher matcher = pattern.matcher(mensaje);
                                matcher.find();
                                String elemento = matcher.group(2).replace("_"," ").toLowerCase();
                                mensajeError = elemento.substring(0,1).toUpperCase()+elemento.substring(1)+" duplicado(a)";
                                break;
                            case 2292:
                                mensajeError = "El registro tiene dependencias";
                                break;
                        }
                    }else if(driver.contains("SQLServerDriver")){
                        switch(errorCode){
                            case 2627:
                                mensajeError = "La clave ya existe";
                                break;
                        }
                    }else if(driver.contains("mysql.jdbc.Driver")){
                        switch(errorCode){
                            case 1062:
                                mensajeError = "La clave ya existe";
                                break;
                        }
                    }else if(driver.contains("DB2Driver")){
                        switch(errorCode){
                        }
                    }
                }
            }else {
                mensajeError = "Los datos de la factura actual, no cumplen con los requisitos fiscales";
            }
        return mensajeError;
    }
}