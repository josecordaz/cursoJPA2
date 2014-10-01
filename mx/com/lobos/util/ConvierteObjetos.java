/*
 * 
 *    ___________________________________________________________________
 *   |            COPYRIGHT (C) BY                                         |
 *   |            DERECHOS RESERVADOS (C) POR                              |
 *   |            LOBO SOFTWARE S.A. DE  C.V.                            |
 *   |                                                                   |
 *   | Ninguna parte de esta obra, parcial o total puede                 |
 *   | ser reproducida o transmitida, mediante ningï¿½n sistema            |
 *   | o mï¿½todo electrï¿½nico o mecï¿½nico (incluyendo el                    |
 *   | fotocopiado, la grabaciï¿½n, o cualquier sistema de                 |
 *   | recuperaciï¿½n y almacenamiento de informaciï¿½n),                    |
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
 *   Document   : ConvierteObjetos
 *   Created on : Sep 27, 2012, 12:40:44 PM
 *   Author     : MFOB
 *   Modificaciones:
 *      JCOC 28/Feb/2013 18:57 - Se sobrecargó metodo generaJsonString para 
 * cuando es necesario mapear tipos de datos diferentes a los de la entitie (JPA)
 *      MFOB - JCOC 09:42 - Se creo generaJSONSringJPQL para la lista que regresa una createQuery que utiliza JPQL
 */
package mx.com.lobos.util;

import java.util.HashMap;
import java.util.List;
import mx.com.lobos.ex.LoboException;
import mx.com.lobos.ex.MsgError;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 *
 * @author marioo
 */
public class ConvierteObjetos {
    
    protected static final MsgError MSG_ERROR = new MsgError();
    
    public static String generaJsonString(boolean success, String message, Integer total, Object data) throws LoboException{
        HashMap<String, Object> jsonStringHsm = new HashMap<String, Object>();
        JSONArray               datosJar;
        String                  jsonData = "";
        try{
            
            jsonStringHsm.put("success",    success);
            jsonStringHsm.put("message",    message);
            jsonStringHsm.put("total",      total   != null ? total : 0     );
            jsonStringHsm.put("data",       data    != null ? data  : "[]"  );
            
            JsonConfig config= new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            
            datosJar = new JSONArray();
            datosJar.add(jsonStringHsm, config);
            jsonData = datosJar.size() > 0 ? datosJar.getJSONObject(0).toString() : datosJar.toString();
            
        }catch(Exception ex){
            throw new LoboException(MSG_ERROR.getMsgError(ex, "generaJsonString"));
        }
        return jsonData;
    }
    
    public static String generaJsonString(boolean success, String message, Integer total, List<Object[]> data,String[] headers) throws LoboException{
        HashMap<String, Object> jsonStringHsm = new HashMap<String, Object>();
        JSONArray               datosJar;
        String                  jsonData = "";
        JSONArray               jsonArray;
        JSONObject              objJSON;
        try{
            
            jsonArray = new JSONArray();
            
            JsonConfig config= new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            
            for(Object[] reg:data){
                objJSON = new JSONObject();
                for(int i = 0; i < headers.length; i++){
                    objJSON.put(headers[i],reg[i]);
                }
                jsonArray.add(objJSON,config);
            }
            
            jsonStringHsm.put("success",    success);
            jsonStringHsm.put("message",    message);
            jsonStringHsm.put("total",      total   != null ? total : 0     );
            jsonStringHsm.put("data",       jsonArray    != null ? jsonArray  : "[]"  );
            
            datosJar = new JSONArray();
            datosJar.add(jsonStringHsm, config);
            jsonData = datosJar.size() > 0 ? datosJar.getJSONObject(0).toString() : datosJar.toString();
            
        }catch(Exception ex){
            throw new LoboException(MSG_ERROR.getMsgError(ex, "generaJsonString"));
        }
        return jsonData;
    }
    public static List mapeaListaObjetosEncabezados( List<Object[]> data,String[] headers) throws LoboException{
        HashMap<String, Object> jsonStringHsm = new HashMap<String, Object>();
        JSONArray               datosJar;
        String                  jsonData = "";
        JSONArray               jsonArray;
        JSONObject              objJSON;
        try{
            jsonArray = new JSONArray();
            
            JsonConfig config= new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            
            for(Object[] reg:data){
                objJSON = new JSONObject();
                for(int i = 0; i < headers.length; i++){
                    objJSON.put(headers[i],reg[i]);
                }
                jsonArray.add(objJSON,config);
            }
            
        }catch(Exception ex){
            throw new LoboException(MSG_ERROR.getMsgError(ex, "mapeaListaObjetosEncabezados"));
        }
        return jsonArray;
    }
    public static String generaJsonStringJPQL(boolean success, String message, Integer total, List data) throws LoboException{
        HashMap<String, Object> jsonStringHsm = new HashMap<String, Object>();
        JSONArray               datosJar;
        String                  jsonData = "";
        JSONArray               jsonArray;
        JSONObject              objJSON;
        try{
            
            jsonArray = new JSONArray();
            
            JsonConfig config = new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            
            for(int i = 0, t = data.size(); i < t; i++){
                objJSON = JSONObject.fromObject(((Object[]) data.get(i))[0], config);
                jsonArray.add(objJSON,config);
            }
            
            jsonStringHsm.put("success",    success);
            jsonStringHsm.put("message",    message);
            jsonStringHsm.put("total",      total   != null ? total : 0     );
            jsonStringHsm.put("data",       jsonArray    != null ? jsonArray  : "[]"  );
            
            datosJar = new JSONArray();
            datosJar.add(jsonStringHsm, config);
            jsonData = datosJar.size() > 0 ? datosJar.getJSONObject(0).toString() : datosJar.toString();
            
        }catch(Exception ex){
            throw new LoboException(MSG_ERROR.getMsgError(ex, "generaJsonString"));
        }
        return jsonData;
    }
    
}
