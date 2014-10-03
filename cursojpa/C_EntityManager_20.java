package cursojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mx.com.lobos.util.ConvierteObjetos;
import mx.com.lobos.util.JSONUtil;

public class C_EntityManager_20 {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em =  null;
        Query query;
        List<Object[]> partidos;
        String headers[];
        String res;
        StringBuilder consulta;
        try{
            emf = Persistence.createEntityManagerFactory("cursoJPAPU");
            em = emf.createEntityManager();
            
            headers = new String[]{"id","fase","fecha","idEquipo1","idEquipo2","idEstadio"};
            
            consulta = new StringBuilder();
            
            consulta.append("SELECT id,");
            consulta.append("  fase, ");
            consulta.append("  fecha, ");
            consulta.append("  id_equipo1, ");
            consulta.append("  id_equipo2, ");
            consulta.append("  id_estadio ");
            consulta.append("FROM partidos");
            
            query = em.createNativeQuery(consulta.toString());
            
            partidos = query.getResultList();
            res = ConvierteObjetos.generaJsonString(true,"Consulta exitosa", partidos.size(),partidos,headers);
            
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