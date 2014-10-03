package cursojpa;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.entities.Equipos;
import mx.com.lobos.entities.Estadios;
import mx.com.lobos.entities.Partidos;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_33 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Partidos> equipos;
        String res;
        Equipos equipo;
        int idEquipo = 5;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            em.getTransaction().begin();
            
            query = em.createNamedQuery("Equipos.findByIdEquipo");
            query.setParameter("idEquipo",idEquipo);
            
            equipos = query.getResultList();
            
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", equipos.size(),equipos);
            System.out.println(JSONUtil.formatJSONPretty(res));
            
            equipo = em.getReference(Equipos.class,idEquipo);
            equipo.setNombre("SPAIN");
            
            em.merge(equipo);
            
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