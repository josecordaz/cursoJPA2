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

public class C_EntityManager_32_ex4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Partidos> partidos;
        String res;
        Partidos partido;
        Calendar calendar;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            calendar = Calendar.getInstance();
            calendar.set(2014,8,15);
            
            em.getTransaction().begin();
            
            partido = new Partidos();
            partido.setFase(true);
            partido.setFecha(calendar.getTime());
            partido.setId(0);
            partido.setIdEquipo1(
                (Equipos) em
                    .createNamedQuery("Equipos.findByAbreviatura")
                    .setParameter("abreviatura","SNT")
                    .getSingleResult()
            );
            partido.setIdEquipo2(
                em
                    .createNamedQuery("Equipos.findByAbreviatura",Equipos.class)
                    .setParameter("abreviatura","BYM")
                    .getSingleResult()
            );
            partido.setIdEstadio(
                (Estadios) em
                    .createNamedQuery("Estadios.findByCiudad")
                    .setParameter("ciudad","Londres")
                    .getSingleResult()
            );
        
            em.persist(partido);
            
            em.getTransaction().commit();
            
            query = em.createNamedQuery("Partidos.findAll");
            
            partidos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", partidos.size(),partidos);
            
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