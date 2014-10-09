package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_10 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> equipos;
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
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos,headers);
            
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