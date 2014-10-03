package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

public class C_EntityManager_10_1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        TypedQuery tQuery;
        List<Object[]> equipos;
        List<JSONObject> lista;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            StringBuilder consulta = new StringBuilder();
            consulta.append("select e.abreviatura,e.nombre ");
            consulta.append("from Equipos e");
            
            query = em.createQuery(consulta.toString());
            
            String headers[]= {"abreviatura","nombre"};
            
            equipos = query.getResultList();
            
                System.out.println(((Object[])equipos.get(0))[0]);
            
            lista = ConvierteObjetos.mapeaListaObjetosEncabezados(equipos, headers);
            
                System.out.println(lista.get(0).getString("nombre"));
            
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),lista);
            
            //res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos,headers);
        /**/System.out.println(JSONUtil.formatJSONPretty(res));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } finally {
            if(em != null){
                em.close();
            }
            if(emf != null){
                emf.close();
            }
        }
    }
}