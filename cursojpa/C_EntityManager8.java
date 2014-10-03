package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.entities.Estadios;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager8 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Estadios> estadios;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            StringBuilder consulta = new StringBuilder();
            consulta.append("select es ");
            consulta.append("from Estadios es");
            
            query = em.createQuery(consulta.toString());
            
            estadios = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", estadios.size(), estadios);
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