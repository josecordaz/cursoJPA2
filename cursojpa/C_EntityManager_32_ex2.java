package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.entities.Grupos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_32_ex2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Grupos> grupos;
        String res;
        Grupos grupo;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            
            grupo = new Grupos();
            grupo.setIdGrupo(0);
            grupo.setNombre("Y");
            
            em.persist(grupo);
            
            em.getTransaction().commit();
            
            query = em.createNamedQuery("Grupos.findAll");
            
            grupos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", grupos.size(),grupos);
            
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