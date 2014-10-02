package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.controllers.GruposJpaController;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.entities.Grupos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Equipos> equipos;
        GruposJpaController gjc;
        String res;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            
            gjc = new GruposJpaController(emf);
            
            em = emf.createEntityManager();
            query = em.createNamedQuery("Equipos.findByIdGrupo");
            query.setParameter("idGrupo",gjc.findGrupos(3));
            
            
            equipos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(), equipos);
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