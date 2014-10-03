package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_29 {
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
            
            headers = new String[]{"nombreEquipoUno","nombreEquipoDos"};
            
            consulta = new StringBuilder();

            consulta.append("SELECT t0.nombre, ");
            consulta.append("  t2.nombre ");
            consulta.append("FROM estadios t3, ");
            consulta.append("  equipos t2, ");
            consulta.append("  partidos t1, ");
            consulta.append("  equipos t0 ");
            consulta.append("WHERE ((t3.ciudad    = ?ciudad) ");
            consulta.append("AND (((t3.id_estadio = t1.id_estadio) ");
            consulta.append("AND (t0.id_equipo    = t1.id_equipo1)) ");
            consulta.append("AND (t2.id_equipo    = t1.id_equipo2)))");
            
            query = em.createNativeQuery(consulta.toString());
            query.setParameter("ciudad","Rio De Janeiro");
            
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