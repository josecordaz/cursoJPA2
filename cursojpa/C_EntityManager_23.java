package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_23 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> equipos;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"abreviatura","nombre"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT abreviatura, ");
            consulta.append("    nombre ");
            consulta.append("FROM equipos");
            
            query = em.createNativeQuery(consulta.toString());
            
            equipos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos,headers);
            
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