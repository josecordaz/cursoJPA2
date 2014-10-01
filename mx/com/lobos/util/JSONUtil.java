package mx.com.lobos.util;

import mx.com.lobos.ex.LoboException;
import mx.com.lobos.ex.MsgError;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtil {
    final static MsgError msgError = new MsgError();
    public static String formatJSONPretty(String strJSON)throws LoboException{
        ObjectMapper mapper;
        Object json;
        String res;
        try{
            mapper = new ObjectMapper();
            json = mapper.readValue(strJSON, Object.class);
            res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (Exception ex) {
            throw new LoboException(msgError.getMsgError(ex, "formatJSONPretty"));
        }
        return res;
    }
}