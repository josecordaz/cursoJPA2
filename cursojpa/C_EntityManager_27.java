package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_27 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> estadios;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"id","fase","fecha","idEquipo1","idEquipo2","idEstadio"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT t1.id, ");
            consulta.append("    t1.fase, ");
            consulta.append("    t1.fecha, ");
            consulta.append("    t1.id_equipo1, ");
            consulta.append("    t1.id_equipo2, ");
            consulta.append("    t1.id_estadio ");
            consulta.append("FROM estadios t0, ");
            consulta.append("    partidos t1 ");
            consulta.append("WHERE ((t0.nombre = ?nombreEstadio) ");
            consulta.append("    AND (t0.id_estadio = t1.id_estadio))");
            
            query = em.createNativeQuery(consulta.toString());
            query.setParameter("nombreEstadio","Arena de Sao Paulo");
            
            estadios = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", estadios.size(),estadios,headers);
            
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