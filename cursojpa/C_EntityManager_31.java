package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_31 {
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
            
            headers = new String[]{"idEstadio","ciudad","nombre"};
            
            consulta = new StringBuilder();

            consulta.append("SELECT DISTINCT t0.id_estadio, ");
            consulta.append("  t0.ciudad, ");
            consulta.append("  t0.nombre ");
            consulta.append("FROM equipos t5, ");
            consulta.append("  grupos t4, ");
            consulta.append("  equipos t3, ");
            consulta.append("  grupos t2, ");
            consulta.append("  partidos t1, ");
            consulta.append("  estadios t0 ");
            consulta.append("WHERE (((t2.nombre    = ?nombreGrupo) ");
            consulta.append("OR (t4.nombre         = ?nombreGrupo)) ");
            consulta.append("AND (((((t3.id_equipo = t1.id_equipo1) ");
            consulta.append("AND (t2.id_grupo      = t3.id_grupo)) ");
            consulta.append("AND (t5.id_equipo     = t1.id_equipo2)) ");
            consulta.append("AND (t4.id_grupo      = t5.id_grupo)) ");
            consulta.append("AND (t0.id_estadio    = t1.id_estadio)))");
            
            query = em.createNativeQuery(consulta.toString());
            query.setParameter("nombreGrupo","D");
            
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