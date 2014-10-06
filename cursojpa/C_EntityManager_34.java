package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_34 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Equipos> equipos;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            
            query = em.createNamedQuery("Equipos.findByAbreviatura");
            query.setParameter("abreviatura","SNT");
            
            equipos = query.getResultList();
            
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos);
            System.out.println(JSONUtil.formatJSONPretty(res));
            
            em.remove(equipos.get(0));
            
            equipos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos);
            
            em.getTransaction().commit();
        /**/System.out.println(JSONUtil.formatJSONPretty(res));
        } catch (Exception ex){
            if(em != null && em.getTransaction() != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
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